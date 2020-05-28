package com.example.Betting.service;

import java.util.Collection;

import com.example.Betting.model.Transaction;

/**
 * The Interface ITransactionService.
 */
public interface ITransactionService {

	/**
	 * Find all transactions.
	 *
	 * @return the collection of transactions
	 */
	Collection<Transaction> findAllTransactions();

	/**
	 * Creates the transaction.
	 *
	 * @param newTransaction the new transaction
	 * @param transactionType the transaction type
	 * @return the transaction
	 */
	Transaction createTransaction(Transaction newTransaction, boolean transactionType);
}
