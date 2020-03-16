package com.example.Betting.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Betting.model.TicketOdds;
import com.example.Betting.repository.TicketOddsRepository;

@Service
public class TicketOddsService {

	@Autowired
	private TicketOddsRepository ticketOddsRepository;
	
	
	public Collection<TicketOdds> findAllPlayedTickets(){
		return ticketOddsRepository.findAll();
	}
	
	public void createTicketOddsPair(TicketOdds ticketOdd) {
		ticketOddsRepository.save(ticketOdd);
	}
	
}
