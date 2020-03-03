package com.example.Betting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Betting.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>  {
	Ticket findById(long Id);
}
