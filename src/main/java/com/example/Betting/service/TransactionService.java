package com.example.Betting.service;

import java.time.Instant;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Betting.model.Transaction;
import com.example.Betting.repository.TransactionRepository;

/**
 * The Class TransactionService.
 */
@Service
public class TransactionService implements ITransactionService {

	/** The transaction repository. */
	@Autowired
	private TransactionRepository transactionRepository;

	/**
	 * Find all transactions.
	 *
	 * @return the collection of transactions
	 */
	public Collection<Transaction> findAllTransactions() {
		return transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "transactiondate"));
	}

	/**
	 * Creates the transaction.
	 *
	 * @param newTransaction the new transaction
	 * @param transactionType the transaction type, adding money or playing ticket
	 * @return the transaction
	 */
	public Transaction createTransaction(final Transaction newTransaction, final boolean transactionType) {
	    /** Set transaction to current time. */
		newTransaction.setTransactiondate(Instant.now());
		newTransaction.setTransactiontype(transactionType);
		transactionRepository.save(newTransaction);
		return newTransaction;
	}

}
