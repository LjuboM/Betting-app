package com.example.Betting.model;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="transaction")
public class Transaction {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Instant date; //To be modified as date type... some kind of date type
	
	//Type of transaction, 0 Represents adding money to account, 1 represents ticket payment
	private boolean type;
	
	private float money;

	@ManyToOne(cascade=CascadeType.PERSIST)//We can't have a user that doesn't exist in user table.
    @JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne(mappedBy = "transaction")
	private Ticket ticket;
}
