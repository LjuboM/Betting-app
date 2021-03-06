package com.example.Betting.repository;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Betting.model.Transaction;

/**
 * The Interface TransactionRepository.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Find all transactions by transaction type.
     *
     * @param type the transaction type
     * @param sort sorting by date descending from newer to older
     * @return the collection of transactions of asked type
     */
    Collection<Transaction> findAllByTransactiontype(int type, Sort sort);
}
