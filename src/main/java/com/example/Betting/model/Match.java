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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name="match")
public class Match {

	@Id
	@JsonIdentityReference(alwaysAsId = true)
	private long id;
	
	private Instant match_date;
	
	private String home;
	
	private String away;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "types_id")
	//@JsonManagedReference
	private Types types;
	
	@OneToMany(mappedBy = "match")
	@JsonBackReference
	private Set<Odds> odds;

}