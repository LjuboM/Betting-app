package com.example.Betting.model;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instantiates a new odds with all arguments.
 */
@AllArgsConstructor

/**
 * Instantiates a new odds.
 */
@NoArgsConstructor
@Entity

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@Table(name = "odds")
public class Odds {

	/** The id. */
	@Id
	@JsonIdentityReference(alwaysAsId = true)
	private long id;

	/** The type. */
	private String type;

	/** The odd 1. */
	private float odd1;

	/** The odd 2. */
	private float odd2;

	/** The odd 3. */
	private float odd3;

	/** The odd 4. */
	private float odd4;

	/** The odd 5. */
	private float odd5;

	/** The odd 6. */
	private float odd6;

	/** The match. */
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "match_id")
	private Match match;

	/** The ticket odds. */
	@OneToMany(mappedBy = "odds")
	@JsonBackReference
	private Set<TicketOdds> ticketOdds;
}
