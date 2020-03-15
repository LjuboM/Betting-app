package com.example.Betting.service;

import java.util.Collection;

import com.example.Betting.model.Transaction;

public interface ITransactionService {

	Collection<Transaction> findAllTransactions();
	
	Transaction createTransaction(Transaction newTransaction, boolean transactionType);
}
