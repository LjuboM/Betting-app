package com.example.Betting.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class TicketOddsKey implements Serializable{

    private long ticket_id;
 
    private long odds_id;
}
