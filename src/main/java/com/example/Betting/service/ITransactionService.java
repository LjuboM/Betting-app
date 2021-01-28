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
	 * @param spentMoney spent money
	 * @param user user
	 * @param ticket ticket
	 * @param transactionType transaction type
	 * @return the transaction
	 */
	Transaction createTransaction(float spentMoney, User user, Ticket ticket, int transactionType);
}
