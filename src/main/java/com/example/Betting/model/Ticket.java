package com.example.Betting.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="ticket")
public class Ticket {

	@Id
	private long id;

	private float totalodd;

	private float possiblegain;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "transaction_id", referencedColumnName = "id")
	@JsonManagedReference
	private Transaction transaction;
	
	@OneToMany(mappedBy = "ticket")
	@JsonManagedReference
	Set<TicketOdds> ticket_odds;
}