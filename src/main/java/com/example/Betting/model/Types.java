package com.example.Betting.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="types")
public class Types {

	@Id
	private long id;

	private String name;
	
	private String type1;
	
	private String type2;
	
	private String type3;
	
	private String type4;
	
	private String type5;
	
	private String type6;

	@OneToMany(mappedBy = "types")
	@JsonBackReference
	private Set<Match> match;
}