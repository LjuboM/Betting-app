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
        System.out.println("0");
        Transaction newTransaction = new Transaction();
        System.out.println("1");
        /** Set transaction to current time. */
        newTransaction.setTransactiondate(Instant.now());
        System.out.println("2");
        newTransaction.setTransactiontype(transactionType);
        System.out.println("3");
        newTransaction.setMoney(spentMoney);
        System.out.println("4");
        newTransaction.setTicket(ticket);
        System.out.println("5");
        newTransaction.setUser(user);
        System.out.println("6");
        transactionRepository.save(newTransaction);
        System.out.println("7");
        return newTransaction;
    }

}
