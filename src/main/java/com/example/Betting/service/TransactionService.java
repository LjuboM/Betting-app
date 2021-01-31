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
     * Find all transactions of asked type.
     *
     * @param type the transaction type
     * @return the collection of transactions of asked type
     */
    public Collection<Transaction> findAllTransactionTypes(final int type) {
        return transactionRepository.findAllByTransactiontype(type, Sort.by(Sort.Direction.DESC, "transactiondate"));
    }

    /**
     * Creates the transaction.
     *
     * @param transaction the transaction
     * @param transactionType transaction type
     * @return true for successful transaction, false otherwise
     */
    public boolean createTransaction(final Transaction transaction, final int transactionType) {
        //Check if the user is adding enough money.
        if (transaction.getMoney() < 1) {
            return false;
        }
        float spentMoney = transaction.getMoney();
        /** Set transaction to current time. */
        transaction.setTransactiondate(Instant.now());
        transaction.setTransactiontype(transactionType);

        //If user just played new ticket
        if (transactionType == 1) {
            final float manipulativeSpends = 0.05f;
            transaction.setManipulativespends(spentMoney * manipulativeSpends);
        }
        transactionRepository.save(transaction);
        return true;
    }

}
