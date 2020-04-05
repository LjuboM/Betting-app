package com.example.Betting.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.TicketOdds;
import com.example.Betting.model.Transaction;
import com.example.Betting.model.User;
import com.example.Betting.service.TicketOddsService;
import com.example.Betting.service.TransactionService;
import com.example.Betting.service.UserService;


@RestController
@RequestMapping("/api")
public class TicketOddsController {
	
	@Autowired
	private TicketOddsService ticketOddsService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/ticketOdds")
	ResponseEntity<?> getTicketOdds(){
		Collection<TicketOdds> allTicketOdds = ticketOddsService.findAllPlayedTickets();
		return ResponseEntity.status(HttpStatus.OK).body(allTicketOdds);
	}
	
	@GetMapping("/ticketOdds/{ticketId}")
	ResponseEntity<?> getMatchPairsByTicketId(@PathVariable Long ticketId){
		Collection<TicketOdds> ticketOdds = ticketOddsService.findAllPlayedPairsByTicketId(ticketId);
		return ResponseEntity.status(HttpStatus.OK).body(ticketOdds);
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
		Optional<User> user = userService.findUserById(first.getTicket().getTransaction().getUser().getId());
		int moneyInWallet = user.get().getMoney();
		int spentMoney = first.getTicket().getTransaction().getMoney();
		
		if(spentMoney < 1) {
			return ResponseEntity.badRequest().body("You have to bet at least 1 HRK");
		}
		
		if(spentMoney > moneyInWallet) {
			return ResponseEntity.badRequest().body("You don't have enough money in your wallet.");
		}
		//Create transaction with current time of type true.
		Transaction transaction = transactionService.createTransaction(first.getTicket().getTransaction(), true);
		//Spending money.
		user = userService.changeMoneyValueInWallet(user.get().getId(), transaction.getMoney(), false);
		
		//Saving first ticket-odds pair so we can use generated IDs to forward them to other ticket-odds pairs
		ticketOddsService.createTicketOddsPair(first);
        ticketOdds.iterator().next();
        
        ticketOdds.iterator().forEachRemaining( ticketOdd -> {
		//Giving same IDs of Ticket and Transaction to rest of ticket-odds pairs.
		ticketOdd.setTicket(first.getTicket());
		ticketOddsService.createTicketOddsPair(ticketOdd);
	});
        return ResponseEntity.ok().body("Successfully placed a bet!");
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
//Should I add check if 2 same matches are played regardless it's type?
