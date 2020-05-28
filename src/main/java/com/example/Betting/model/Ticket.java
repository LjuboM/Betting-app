package com.example.Betting.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instantiates a new ticket with all arguments.
 */
@AllArgsConstructor

/**
 * Instantiates a new ticket.
 */
@NoArgsConstructor
@Entity

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@Table(name = "ticket")
public class Ticket {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The total odd. */
	private float totalodd;

	/** The possible gain. */
	private float possiblegain;

	/** The transaction. */
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "transaction_id", referencedColumnName = "id")
	private Transaction transaction;

	/** The ticket odds. */
	@OneToMany(mappedBy = "ticket")
	@JsonBackReference
	private Set<TicketOdds> ticketOdds;
}
