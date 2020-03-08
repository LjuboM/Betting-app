package com.example.Betting.controller;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.Transaction;
import com.example.Betting.repository.TransactionRepository;

@RestController
@RequestMapping("/api")
public class TransactionController {
	private TransactionRepository transactionRepository;

	public TransactionController(TransactionRepository transactionRepository) {
		super();
		this.transactionRepository = transactionRepository;
	}
	
	@GetMapping("/transactions")
	Collection<Transaction> transactions(){
		return transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "transdate"));
	}
}
