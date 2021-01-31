package com.example.Betting.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Betting.model.Ticket;
import com.example.Betting.model.TicketOdds;

/**
 * The Interface TicketOddsRepository.
 */
public interface TicketOddsRepository extends JpaRepository<TicketOdds, Long> {

	/**
	 * Find all by ticket id.
	 *
	 * @param ticketId the ticket id
	 * @return the collection of ticket odds
	 */
	Collection<TicketOdds> findAllByTicketId(long ticketId);

	 /**
     * Save new ticket.
     *
     * @param ticket new ticket
     */
    void save(Ticket ticket);

}
