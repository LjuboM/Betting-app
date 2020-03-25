package com.example.Betting.service;

import java.util.Collection;

import com.example.Betting.model.Ticket;

public interface ITicketService {

	Collection<Ticket> findAllTickets();
}
