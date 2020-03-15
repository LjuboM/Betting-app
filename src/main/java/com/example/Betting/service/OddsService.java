package com.example.Betting.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Betting.model.Odds;
import com.example.Betting.repository.OddsRepository;

@Service
public class OddsService {

	@Autowired
	private OddsRepository oddsRepository;
	
	public Collection<Odds> findAllSortedOdds(){
		Collection<Odds> odds = oddsRepository.findAll(Sort.by(Sort.Direction.DESC, "match.types.id"));
		
		//Filter by date, keep only matches that didn't occur yet.
		Collection<Odds> oldOdds = new ArrayList();
		Instant currentTime = Instant.now();
		odds.forEach(odd -> {
    		if(odd.getMatch().getMatchdate().compareTo(currentTime) < 1) {
    	    	oldOdds.add(odd);
    		}
		});
		odds.removeAll(oldOdds);
		return odds;
	}
}
