package com.example.Betting.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="odds")
public class Odds {

	@Id
	private long id;

	private boolean type;
	
	private float odd1;
	
	private float odd2;
	
	private float odd3;
	
	private float odd4;
	
	private float odd5;
	
	private float odd6;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "match_id")
	private Match match;
	
	@ManyToMany(mappedBy = "odds")
	Set<Ticket> ticket;
}