package com.example.Betting.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Betting.model.Odds;
import com.example.Betting.repository.OddsRepository;

/**
 * The Class OddsService.
 */
@Service
public class OddsService implements IOddsService {

	/** The odds repository. */
	@Autowired
	private OddsRepository oddsRepository;

	/**
	 * Find all sorted odds.
	 * Filter by date, keep only matches that didn't occur yet.
	 *
	 * @return the collection of odds
	 */
	public Collection<Odds> findAllSortedOdds() {
		Collection<Odds> odds = oddsRepository.findAll(Sort.by(Sort.Direction.ASC, "match.matchdate"));

		Collection<Odds> oldOdds = new ArrayList<Odds>();
		Instant currentTime = Instant.now();
		odds.forEach(odd -> {
    		if (odd.getMatch().getMatchdate().compareTo(currentTime) < 1) {
    	    	oldOdds.add(odd);
    		}
		});
		odds.removeAll(oldOdds);
		return odds;
	}
}
