package com.example.Betting.service;

import java.time.Instant;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Betting.model.Ticket;
import com.example.Betting.model.Transaction;
import com.example.Betting.model.User;
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
     * @param sort sorting by date descending from newer to older
     * @return the collection of transactions of asked type
     */
    public Collection<Transaction> findAllTransactionTypes(final int type) {
        return transactionRepository.findAllByTransactiontype(type, Sort.by(Sort.Direction.DESC, "transactiondate"));
    }
    
    /**
     * Creates the transaction.
     *
     * @param spentMoney spent money
     * @param user user
     * @param ticket ticket
     * @param transactionType transaction type
     * @return the transaction
     */
    public Transaction createTransaction(
            final float spentMoney, final User user, final Ticket ticket, final int transactionType) {

        Transaction newTransaction = new Transaction();
        /** Set transaction to current time. */
        newTransaction.setTransactiondate(Instant.now());
        newTransaction.setTransactiontype(transactionType);
        newTransaction.setMoney(spentMoney);
        newTransaction.setTicket(ticket);
        newTransaction.setUser(user);

        transactionRepository.save(newTransaction);
        return newTransaction;
    }

}
