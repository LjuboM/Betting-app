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
import com.example.Betting.utils.Constants;

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
     * @param money the transaction money
     * @param ticket the ticket
     * @param user the user
     * @param transactionType transaction type
     * @return true for successful transaction, false otherwise
     */
    public boolean createTransaction(
            final float money, final Ticket ticket, final User user, final int transactionType) {
        final Transaction transaction = new Transaction();
        //Check if the user is adding or betting enough money.
        if (money < 1) {
            return false;
        }
        float spentMoney = money;
        /** Set transaction to current time. */
        transaction.setTransactiondate(Instant.now());
        transaction.setTransactiontype(transactionType);
        transaction.setMoney(money);
        transaction.setUser(user);
        transaction.setTicket(ticket);

        //If user just played new ticket
        if (transactionType == Constants.TYPE_TICKET_PAYMENT) {
            final float manipulativeSpends = 0.05f;
            transaction.setManipulativespends(spentMoney * manipulativeSpends);
        }
        transactionRepository.save(transaction);
        return true;
    }

}
