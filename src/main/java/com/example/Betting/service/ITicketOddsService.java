package com.example.Betting.service;

import java.util.Collection;

import com.example.Betting.model.TicketOdds;

public interface ITicketOddsService {
	
	Collection<TicketOdds> findAllPlayedTickets();
	
	TicketOdds createTicketOddsPair(TicketOdds ticketOdd);
}
