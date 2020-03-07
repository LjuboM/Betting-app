package com.example.Betting.model;

import java.time.Instant;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name="match")
public class Match {

	@Id
	private long id;
	
	private Instant match_date;
	
	private String home;
	
	private String away;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "types_id")
	private Types types;
	
	@OneToMany(mappedBy = "match")
	private Set<Odds> odds;

}