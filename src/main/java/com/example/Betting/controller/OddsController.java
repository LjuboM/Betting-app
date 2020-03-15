package com.example.Betting.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.Odds;
import com.example.Betting.service.OddsService;

@RestController
@RequestMapping("/api")
public class OddsController {

	@Autowired
	OddsService oddsService;
	
	@GetMapping("/odds")
	Collection<Odds> getOddsForFutureMatches(){
		return oddsService.findAllSortedOdds();
	}
}
