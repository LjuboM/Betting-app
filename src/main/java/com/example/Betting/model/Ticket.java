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


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@Table(name="ticket")
public class Ticket {

	@Id
	//@JsonIdentityReference(alwaysAsId = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private float totalodd;

	private float possiblegain;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "transaction_id", referencedColumnName = "id")
	private Transaction transaction;
	
	@OneToMany(mappedBy = "ticket")
	@JsonBackReference
	Set<TicketOdds> ticket_odds;
}