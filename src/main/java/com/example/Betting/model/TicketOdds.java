package com.example.Betting.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@Table(name="ticket_odds")
public class TicketOdds {

	@Id
	@JsonIdentityReference(alwaysAsId = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name = "odds_id")
    private Odds odds;
 
    private float odd;

    private String type;
}
