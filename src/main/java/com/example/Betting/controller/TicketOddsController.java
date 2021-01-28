package com.example.Betting.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.Ticket;
import com.example.Betting.model.TicketOdds;
import com.example.Betting.model.Transaction;
import com.example.Betting.model.User;
import com.example.Betting.service.TicketOddsService;
import com.example.Betting.service.TransactionService;
import com.example.Betting.service.UserService;

/**
 * The Class TicketOddsController.
 */
@RestController
@RequestMapping("/api")
public class TicketOddsController {

	/** The ticket odds service. */
	@Autowired
	private TicketOddsService ticketOddsService;

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The transaction service. */
	@Autowired
	private TransactionService transactionService;

	/**
	 * Gets the ticket odds.
	 *
	 * @return the ticket odds
	 */
	@GetMapping("/ticketOdds")
	ResponseEntity<?> getTicketOdds() {
		Collection<TicketOdds> allTicketOdds = ticketOddsService.findAllPlayedTicketPairs();
		return ResponseEntity.status(HttpStatus.OK).body(allTicketOdds);
	}

	/**
	 * Gets the match pairs by ticket id.
	 *
	 * @param ticketId the ticket id
	 * @return the match pairs by ticket id
	 */
	@GetMapping("/ticketOdds/{ticketId}")
	ResponseEntity<?> getMatchPairsByTicketId(@PathVariable final Long ticketId) {
		Collection<TicketOdds> ticketOdds = ticketOddsService.findAllPlayedPairsByTicketId(ticketId);
		if (CollectionUtils.isEmpty(ticketOdds)) {
		    return ResponseEntity.notFound().build();
		} else {
		    return ResponseEntity.status(HttpStatus.OK).body(ticketOdds);
		}
	}

	/**
	 * Playing ticket.
	 *
	 * @param ticketOdds the ticket odds
	 * @return the response entity
	 */
    @RequestMapping(value = "/ticket", method = RequestMethod.POST)
    public ResponseEntity<?> playingTicket(@RequestBody final ArrayList<TicketOdds> ticketOdds) {
    	//Check if the ticket is valid.
    	if (!validateTicket(ticketOdds)) {
    		return ResponseEntity.badRequest().body("You didn't place a valid bet.");
    	}
    	TicketOdds first = ticketOdds.iterator().next();

		//Get User who played ticket.
    	//Hardcoded !!!
		Optional<User> user = userService.findUserById(1L);
		float moneyInWallet = user.get().getMoney();

		//0.95 comes from 5% of manipulative spends
		final float manipulativeSpends = 0.95f;
		Ticket ticket = first.getTicket();
		//Needs improvement
		float spentMoney = (
		        ticket.getPossiblegain() + ticket.getTaxes()) / (ticket.getTotalodd() * manipulativeSpends);

		if (spentMoney < 1) {
			return ResponseEntity.badRequest().body("You have to bet at least 1 HRK");
		}

		if (spentMoney > moneyInWallet) {
			return ResponseEntity.badRequest().body("You don't have enough money in your wallet.");
		}

		//Saving first ticket-odds pair so we can use generated IDs to forward them to other ticket-odds pairs
	    ticketOddsService.createTicketOddsPair(first);
	    ticketOdds.iterator().next();

        ticketOdds.iterator().forEachRemaining(ticketOdd -> {
		//Giving same IDs of Ticket and Transaction to rest of ticket-odds pairs.
		ticketOdd.setTicket(ticket);
		ticketOddsService.createTicketOddsPair(ticketOdd);

	    //Create transaction with current time of type 1.
        Transaction transaction;
        transaction = transactionService.createTransaction(spentMoney, user.get(), ticket, 1);

        //Spending money.
        userService.changeMoneyValueInWallet(user.get().getId(), transaction.getMoney(), false);
	});
        return ResponseEntity.ok().body("Successfully placed a bet!");
    }

    /**
     * Validate ticket.
     *
     * @param ticketOdds the ticket odds
     * @return true, if ticket is valid
     */
    private boolean validateTicket(final ArrayList<TicketOdds> ticketOdds) {
        /** Minimum number of basic odds higher than minOddValue needed for valid ticket */
        final int minBiggerOddsCount = 5;
        final float minOddValue = (float) 1.10;
    	float[] odds = new float[ticketOdds.size()];
    	Long[] matches = new Long[ticketOdds.size()];
    	boolean specialOffer = false;
    	long specialOfferMatch = 1;
    	int iterator = 0;
		Instant currentTime = Instant.now();

    	for (TicketOdds ticketOdd : ticketOdds) {
			matches[iterator] = ticketOdd.getOdds().getMatch().getId();
    		if (ticketOdd.getOdds().getType().equals("Basic")) {
    			odds[iterator] = ticketOdd.getOdd();
    		} else if (!specialOffer) {
    			specialOffer = true;
    			specialOfferMatch = ticketOdd.getOdds().getMatch().getId();
    			odds[iterator] = (float) 1.0;
    		} else {
    	    	System.out.println("Invalid bet, more than one Special offer played!");
    			return false;
    		}
    		if (ticketOdd.getOdds().getMatch().getMatchdate().compareTo(currentTime) < 1) {
    	    	System.out.println("Invalid bet, too late, match already started!");
    			return false;
    		}
    		iterator++;
    	}

    	int specialOfferMatchOccurrences = 0;
    	//Number of odds bigger than minOddValue
    	int biggerOddsCount = 0;
    	int matchesIterator = 0;
    	if (specialOffer) {
    		for (Long match : matches) {
    			if (specialOfferMatch == match) {
    				specialOfferMatchOccurrences++;
    			}
    			if (odds[matchesIterator] >= minOddValue) {
    				biggerOddsCount++;
    			}
    			matchesIterator++;
    		}
        	if (specialOfferMatchOccurrences > 1) {
            	System.out.println("Invalid bet, played the same match in special offer and basic type!");
            	return false;
        	}
        	if (biggerOddsCount < minBiggerOddsCount) {
            	System.out.println("Invalid bet, you have to play at least "
            	        + String.valueOf(minBiggerOddsCount) + " basic odds that are "
            	        + String.valueOf(minOddValue) + " or bigger!");
            	return false;
        	}
    	}

		return true;
    }
}
