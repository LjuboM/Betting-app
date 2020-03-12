package com.example.Betting.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.TicketOdds;
import com.example.Betting.model.User;
import com.example.Betting.repository.TicketOddsRepository;
import com.example.Betting.repository.UserRepository;


@RestController
@RequestMapping("/api")
public class TicketOddsController {
	private TicketOddsRepository ticketOddsRepository;
	private UserRepository userRepository;

	public TicketOddsController(TicketOddsRepository ticketOddsRepository, UserRepository userRepository) {
		super();
		this.ticketOddsRepository = ticketOddsRepository;
		this.userRepository = userRepository;
	}

	//Show all played tickets.
	@GetMapping("/tickets")
	Collection<TicketOdds> getTickets(){
		return ticketOddsRepository.findAll();
	}

	//Play a ticket.
    @RequestMapping(value = "/ticket", method = RequestMethod.POST)
    public ResponseEntity<?> playingTicket(@RequestBody ArrayList<TicketOdds> ticketOdds){
    	//Check if the ticket is valid.
    	if(!validateTicket(ticketOdds)) {
    		return ResponseEntity.badRequest().body("You didn't place a valid bet.");
    	}
    	TicketOdds first = ticketOdds.iterator().next();

		//Get User who played ticket. 
		Optional<User> user = userRepository.findById(first.getTicket().getTransaction().getUser().getId());
		float moneyInWallet = user.get().getMoney();
		float spentMoney = first.getTicket().getTransaction().getMoney();
		
		if(spentMoney > moneyInWallet) {
			return ResponseEntity.badRequest().body("You don't have enough money in your wallet.");
		}

		//Spending money.
		float changedMoneyInWallet = moneyInWallet - spentMoney;
		user.get().setMoney(changedMoneyInWallet);
		userRepository.save(user.get());

		//Transaction with current time of type true.
		first.getTicket().getTransaction().setTransactiondate(Instant.now());
		first.getTicket().getTransaction().setTransactiontype(true);
		//Saving first ticket-odds pair so we can use generated IDs to forward them to other ticket-odds pairs
		ticketOddsRepository.save(first);
        ticketOdds.iterator().next();
        
	ticketOdds.iterator().forEachRemaining( ticketOdd -> {
		//Giving same IDs of Ticket and Transaction to rest of ticket-odds pairs.
		ticketOdd.setTicket(first.getTicket());
		ticketOdd.getTicket().setTransaction(first.getTicket().getTransaction());
        ticketOddsRepository.save(ticketOdd);
	});
        return ResponseEntity.ok().body("Succesfully placed a bet!");
    }
    
    //Checks all constraints for a ticket.
    private boolean validateTicket(ArrayList<TicketOdds> ticketOdds){
    	float odds[] = new float[ticketOdds.size()];
    	Long matches[] = new Long[ticketOdds.size()];
    	boolean specialOffer = false;
    	long specialOfferMatch = 1;
    	int iterator = 0;
		Instant currentTime = Instant.now();
    	
    	for(TicketOdds ticketOdd : ticketOdds) {
			matches[iterator] = ticketOdd.getOdds().getMatch().getId();
    		if(ticketOdd.getOdds().getType().equals("Basic")) {
    			odds[iterator] = ticketOdd.getOdd();
    		}
    		else if(specialOffer == false) {
    			specialOffer=true;
    			specialOfferMatch = ticketOdd.getOdds().getMatch().getId();
    			odds[iterator] = (float) 1.0;
    		}
    		else {
    	    	System.out.println("Invalid bet, more than one Special offer played!");
    			return false;
    		}
    		if(ticketOdd.getOdds().getMatch().getMatchdate().compareTo(currentTime) < 1) {
    	    	System.out.println("Invalid bet, too late, match already started!");
    			return false;
    		}
    		iterator++;
    	}
    	
    	int specialOfferMatchOccurrences = 0;
    	//Odds bigger than 1.10
    	int biggerOddsCount = 0;
    	
    	if(specialOffer) {
    		for(Long match : matches) {
    			if(specialOfferMatch == match) {
    				specialOfferMatchOccurrences++;
    			}
    			if(match >= 1.1) {
    				biggerOddsCount++;
    			}
    		}
    		
        	if(specialOfferMatchOccurrences > 1) {
            	System.out.println("Invalid bet, played the same match in special offer and basic type!");
            	return false;
        	}
        	
        	if(biggerOddsCount < 5) {
            	System.out.println("Invalid bet, you have to play at least 5 basic odds that are 1.10 or bigger!");
            	return false;
        	}
    	}
    	
		return true;
    }
}
