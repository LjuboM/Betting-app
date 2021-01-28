package com.example.Betting.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	/** The taxes. */
    private float taxes;

	/**
	 * The Ticket status.
	 * 0 -> Waiting for results
	 * 1 -> Canceled
	 * 2 -> Invalid
	 * 3 -> Victorious
	 * 4 -> Failed
	 */
	private int status;

	/** The transaction. */
	@OneToMany(mappedBy = "ticket")
	@JsonBackReference(value = "transaction")
    private Set<Transaction> transaction;

	/** The ticket odds. */
	@OneToMany(mappedBy = "ticket")
	@JsonBackReference(value = "ticket-odds")
	private Set<TicketOdds> ticketOdds;
}
