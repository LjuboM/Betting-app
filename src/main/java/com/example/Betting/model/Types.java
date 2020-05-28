package com.example.Betting.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instantiates a new types with all arguments.
 */
@AllArgsConstructor

/**
 * Instantiates a new types.
 */
@NoArgsConstructor
@Entity

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@Table(name = "types")
public class Types {

	/** The id. */
	@Id
	@JsonIdentityReference(alwaysAsId = true)
	private long id;

	/** The name. */
	private String name;

	/** The type 1. */
	private String type1;

	/** The type 2. */
	private String type2;

	/** The type 3. */
	private String type3;

	/** The type 4. */
	private String type4;

	/** The type 5. */
	private String type5;

	/** The type 6. */
	private String type6;

	/** The match. */
	@OneToMany(mappedBy = "types")
	@JsonBackReference
	private Set<Match> match;
}
