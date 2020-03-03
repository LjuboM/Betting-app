package com.example.Betting.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="user")
public class User {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int age;
	
	private String location;
	
	private float money;
	
	private String name;
	
	@OneToMany(mappedBy = "user")
	private Set<Transaction> transaction;
}