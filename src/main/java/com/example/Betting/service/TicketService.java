package com.example.Betting.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Betting.model.Ticket;
import com.example.Betting.repository.TicketRepository;

@Service
public class TicketService {
	@Autowired
	private TicketRepository ticketRepository;
	
	public Collection<Ticket> findAllTickets(){
		return ticketRepository.findAll();
	}
	
}
