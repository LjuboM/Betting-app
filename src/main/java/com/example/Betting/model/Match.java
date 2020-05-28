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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instantiates a new match with all arguments.
 */
@AllArgsConstructor

/**
 * Instantiates a new match.
 */
@NoArgsConstructor
@Entity

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@Table(name = "match")
public class Match {

	/** The id. */
	@Id
	@JsonIdentityReference(alwaysAsId = true)
	private long id;

	/** The match date. */
	private Instant matchdate;

	/** The home. */
	private String home;

	/** The away. */
	private String away;

	/** The types. */
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "types_id")
	private Types types;

	/** The odds. */
	@OneToMany(mappedBy = "match")
	@JsonBackReference
	private Set<Odds> odds;

}
