package com.example.Betting.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.Odds;
import com.example.Betting.repository.OddsRepository;

@RestController
@RequestMapping("/api")
public class OddsController {
	private OddsRepository oddsRepository;

	public OddsController(OddsRepository oddsRepository) {
		super();
		this.oddsRepository = oddsRepository;
	}
	//Show all available future matches that user can bet on.
	@GetMapping("/odds")
	Collection<Odds> getOddsForMatches(){
		Collection<Odds> odds = oddsRepository.findAll(Sort.by(Sort.Direction.DESC, "match.types.id"));
		Collection<Odds> oldOdds = new ArrayList();
		Instant currentTime = Instant.now();
		odds.forEach(odd -> {
    		if(odd.getMatch().getMatchdate().compareTo(currentTime) < 1) {
    	    	System.out.println(odd.getMatch().getMatchdate().compareTo(currentTime));
    	    	oldOdds.add(odd);
    		}
		});
		odds.removeAll(oldOdds);
		return odds;
	}
}
