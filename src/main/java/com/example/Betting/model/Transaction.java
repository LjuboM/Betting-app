package com.example.Betting.model;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instantiates a new transaction with all arguments.
 */
@AllArgsConstructor

/**
 * Instantiates a new transaction.
 */
@NoArgsConstructor
@Entity

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@Table(name = "transaction")
public class Transaction {

	/** The id. */
	@Id
	@JsonIdentityReference(alwaysAsId = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The transaction date. */
	private Instant transactiondate;

	/**
	 * The transaction type.
	 * 0 -> Add money to Account
	 * 1 -> Ticket Payment
	 * 2 -> Payout
	 */
	private int transactiontype;

	/** The money. */
	private float money;

	/** The user. */
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
	private User user;

	/** The ticket. */
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;
}
