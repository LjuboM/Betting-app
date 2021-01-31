package com.example.Betting.service;

import java.util.Collection;

import com.example.Betting.model.Ticket;
import com.example.Betting.model.Transaction;
import com.example.Betting.model.User;

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
	 * @param money the transaction money
	 * @param ticket the ticket
	 * @param user the user
     * @param transactionType transaction type
	 * @return true for successful transaction, false otherwise
	 */
	boolean createTransaction(float money, Ticket ticket, User user, int transactionType);

}
