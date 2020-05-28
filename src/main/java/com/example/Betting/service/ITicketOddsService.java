package com.example.Betting.service;

import java.util.Collection;

import com.example.Betting.model.TicketOdds;

/**
 * The Interface ITicketOddsService.
 */
public interface ITicketOddsService {

	/**
	 * Find all played tickets.
	 *
	 * @return the collection of ticket odds
	 */
	Collection<TicketOdds> findAllPlayedTickets();

	/**
	 * Creates the ticket odds pair.
	 *
	 * @param ticketOdd the ticket odd
	 * @return the ticket odds
	 */
	TicketOdds createTicketOddsPair(TicketOdds ticketOdd);

	/**
	 * Find all played pairs by ticket id.
	 *
	 * @param ticketId the ticket id
	 * @return the collection of ticket odds
	 */
	Collection<TicketOdds> findAllPlayedPairsByTicketId(long ticketId);
}
