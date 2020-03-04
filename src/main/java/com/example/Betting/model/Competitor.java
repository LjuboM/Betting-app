package com.example.Betting.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="competitor")
public class Competitor {

	@Id
	private long id;

	private String name;

	private String sport;
	
	@OneToMany(mappedBy = "home")
	private Set<Match> home_match;
	
	@OneToMany(mappedBy = "away")
	private Set<Match> away_match;
}