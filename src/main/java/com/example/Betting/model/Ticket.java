package com.example.Betting.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	private Transaction transaction;
	
	@ManyToMany
	@JoinTable(
	  name = "matches_per_ticket", 
	  joinColumns = @JoinColumn(name = "ticket_id"), 
	  inverseJoinColumns = @JoinColumn(name = "odds_id"))
	Set<Match> odds;
}