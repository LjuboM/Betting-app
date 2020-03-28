package com.example.Betting.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Betting.model.TicketOdds;

public interface TicketOddsRepository extends JpaRepository<TicketOdds, Long> {

	Collection<TicketOdds> findAllByTicket_id(long ticketId);

}
