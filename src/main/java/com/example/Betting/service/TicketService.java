package com.example.Betting.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Betting.model.Ticket;
import com.example.Betting.repository.TicketRepository;

/**
 * The Class TicketService.
 */
@Service
public class TicketService {

	/** The ticket repository. */
	@Autowired
	private TicketRepository ticketRepository;

	/**
	 * Find all tickets.
	 *
	 * @return the collection of tickets
	 */
	public Collection<Ticket> findAllTickets() {
		return ticketRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

}
