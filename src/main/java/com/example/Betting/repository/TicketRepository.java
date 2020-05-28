package com.example.Betting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Betting.model.Ticket;

/**
 * The Interface TicketRepository.
 */
public interface TicketRepository extends JpaRepository<Ticket, Long>  {
}
