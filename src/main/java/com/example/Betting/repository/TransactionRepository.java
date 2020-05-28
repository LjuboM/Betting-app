package com.example.Betting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Betting.model.Transaction;

/**
 * The Interface TransactionRepository.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
