package com.example.Betting.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.Ticket;
import com.example.Betting.service.TicketService;

/**
 * The Class TicketController.
 */
@RestController
@RequestMapping("/api")
public class TicketController {

	/** The ticket service. */
	@Autowired
	private TicketService ticketService;

	/**
	 * Gets the tickets.
	 *
	 * @return the tickets
	 */
	@GetMapping("/tickets")
	ResponseEntity<?> getTickets() {
		Collection<Ticket> allTickets = ticketService.findAllTickets();
		return ResponseEntity.status(HttpStatus.OK).body(allTickets);
	}

}
