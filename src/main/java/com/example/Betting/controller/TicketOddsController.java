package com.example.Betting.controller;

import java.time.Instant;
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
    public ResponseEntity<?> playingTicket(@RequestBody Collection<TicketOdds> ticketOdds){
    	
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
		first.getTicket().getTransaction().setTransdate(Instant.now());
		first.getTicket().getTransaction().setType(true);
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
}
