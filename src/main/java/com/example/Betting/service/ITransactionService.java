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
     * Find all transactions of asked type.
     *
     * @param type the transaction type
     * @return the collection of transactions of asked type
     */
    Collection<Transaction> findAllTransactionTypes(int type);

    /**
	 * Creates the transaction.
	 *
	 * @param transaction the transaction
	 * @param transactionType transaction type
	 * @return true for successful transaction, false otherwise
	 */
	boolean createTransaction(Transaction transaction, int transactionType);
}
