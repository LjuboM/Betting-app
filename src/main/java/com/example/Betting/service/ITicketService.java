package com.example.Betting.service;

import java.util.Collection;

import com.example.Betting.model.Ticket;

/**
 * The Interface ITicketService.
 */
public interface ITicketService {

	/**
	 * Find all tickets.
	 *
	 * @return the collection of tickets
	 */
	Collection<Ticket> findAllTickets();
}
