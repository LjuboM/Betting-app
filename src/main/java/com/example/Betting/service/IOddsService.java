package com.example.Betting.service;

import java.util.Collection;

import com.example.Betting.model.Odds;

public interface IOddsService {
	Collection<Odds> findAllSortedOdds();

}
