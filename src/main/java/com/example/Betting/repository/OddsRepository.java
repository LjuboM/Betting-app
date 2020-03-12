package com.example.Betting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Betting.model.Odds;

public interface OddsRepository extends JpaRepository<Odds, Long>{

}
