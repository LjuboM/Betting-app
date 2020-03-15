package com.example.Betting.service;

import java.time.Instant;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Betting.model.Transaction;
import com.example.Betting.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	public Collection<Transaction> findAllTransactions(){
		return transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "transactiondate"));
	}
	
	public Transaction createTransaction(Transaction newTransaction, boolean transactionType) {
		//Set transaction to current time.
		newTransaction.setTransactiondate(Instant.now());
		newTransaction.setTransactiontype(transactionType);
		transactionRepository.save(newTransaction);
		return newTransaction;
	}
	
	
}
