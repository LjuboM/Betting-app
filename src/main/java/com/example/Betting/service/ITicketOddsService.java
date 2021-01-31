package com.example.Betting.service;

import java.util.ArrayList;
import java.util.Collection;

import com.example.Betting.model.Ticket;
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
	Collection<TicketOdds> findAllPlayedTicketPairs();

	/**
	 * Creates the ticket odds pair.
	 *
	 * @param ticketOdd the ticket odd
	 */
	void createTicketOddsPair(TicketOdds ticketOdd);

	/**
	 * Find all played pairs by ticket id.
	 *
	 * @param ticketId the ticket id
	 * @return the collection of ticket odds
	 */
	Collection<TicketOdds> findAllPlayedPairsByTicketId(long ticketId);

    /**
     * Find all played pairs by ticket id.
     *
     * @param totalOdd the total odd of the ticket
     * @param spentMoney the bet money
     * @return the collection of ticket odds
     */
    Ticket createTicket(float totalOdd, float spentMoney);

	 /**
     * Validates new ticket.
     *
     * @param ticketOdds the ticket odds array
     * @return Total odd value
     */
	float validateNewTicket(ArrayList<TicketOdds> ticketOdds);
}
