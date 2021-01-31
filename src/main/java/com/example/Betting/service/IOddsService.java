package com.example.Betting.service;

import java.util.Collection;
import java.util.Optional;

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

    /**
     * Find Odds by id.
     *
     * @param id the id
     * @return the optional Odds
     */
    Optional<Odds> findOddsById(Long id);
}
