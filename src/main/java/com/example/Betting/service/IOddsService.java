package com.example.Betting.service;

import java.util.Collection;

import com.example.Betting.model.Odds;

/**
 * The Interface IOddsService.
 */
public interface IOddsService {

	/**
	 * Find all sorted odds.
	 *
	 * @return the collection of odds
	 */
	Collection<Odds> findAllSortedOdds();

}
