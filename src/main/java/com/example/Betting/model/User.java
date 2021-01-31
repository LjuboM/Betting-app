package com.example.Betting.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instantiates a new user with all arguments.
 */
@AllArgsConstructor

/**
 * Instantiates a new user.
 */
@NoArgsConstructor
@Entity

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@Table(name = "user")
public class User {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The name. */
	private String name;

	/** The location. */
	private String location;

	/** The age. */
	private int age;

	/** The money. */
	@PositiveOrZero
	private float money;

	/** The transaction. */
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<Transaction> transaction;
}
