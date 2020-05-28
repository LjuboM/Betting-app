package com.example.Betting.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Betting.model.TicketOdds;
import com.example.Betting.repository.TicketOddsRepository;

/**
 * The Class TicketOddsService.
 */
@Service
public class TicketOddsService {

	/** The ticket odds repository. */
	@Autowired
	private TicketOddsRepository ticketOddsRepository;

	/**
	 * Find all played ticket pairs.
	 *
	 * @return the collection of ticket odds
	 */
	public Collection<TicketOdds> findAllPlayedTicketPairs() {
		return ticketOddsRepository.findAll(Sort.by(Sort.Direction.DESC, "odds.match.matchdate"));
	}

	/**
	 * Creates the ticket odds pair.
	 *
	 * @param ticketOdd the ticket odd
	 */
	public void createTicketOddsPair(final TicketOdds ticketOdd) {
		ticketOddsRepository.save(ticketOdd);
	}

	/**
	 * Find all played pairs by ticket id.
	 *
	 * @param ticketId the ticket id
	 * @return the collection of ticket odds
	 */
	public Collection<TicketOdds> findAllPlayedPairsByTicketId(final long ticketId) {
		return ticketOddsRepository.findAllByTicketId(ticketId);
	}

}
