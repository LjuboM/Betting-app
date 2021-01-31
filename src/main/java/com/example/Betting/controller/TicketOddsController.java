package com.example.Betting.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

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
	 * @param spentMoney bet money
	 * @return the response entity
	 */
    @RequestMapping(value = "/ticket/{spentMoney}", method = RequestMethod.POST)
    public ResponseEntity<?> playingTicket(
            @RequestBody final ArrayList<TicketOdds> ticketOdds, @PathVariable final float spentMoney) {

    	//function validateNewTicket returns 0 as total odd if the bet is not valid.
        final float totalOdd = ticketOddsService.validateNewTicket(ticketOdds);
    	if (totalOdd == 0) {
    		return ResponseEntity.badRequest().body("You didn't place a valid bet.");
    	}
    	try {
    	    //Get User who played ticket.
            //1L, otherwise if we had users we would get it from path variable.
            final User user = userService.findUserById(1L).get();

            if (!userService.checkMoneyInWallet(spentMoney, user)) {
                return ResponseEntity.badRequest().body("You don't have enough money in your wallet.");
            } else if (spentMoney < 1) {
                return ResponseEntity.badRequest().body("You have to bet at least 1 HRK");
            }

            Ticket ticket = ticketOddsService.createTicket(totalOdd, spentMoney);

            //Create transaction with current time of type 1
            transactionService.createTransaction(spentMoney, ticket, user, 1);
            //Adding all new ticket odd pairs
            ticketOdds.iterator().forEachRemaining(ticketOdd -> {
                //Attaching new ticket to ticket odd pairs
                ticketOdd.setTicket(ticket);
                //Creating new ticket odd pair
                ticketOddsService.createTicketOddsPair(ticketOdd);
            });

            //Spending money.
            userService.changeMoneyValueInWallet(user, spentMoney, false);
            return ResponseEntity.ok().body("Successfully placed a bet!");

    	} catch (NoSuchElementException exception) {
            return ResponseEntity.badRequest().body("Invalid user!");
    	}
    }

}
