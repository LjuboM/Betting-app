package com.example.Betting.controller;

import java.net.URISyntaxException;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.Transaction;
import com.example.Betting.service.TransactionService;
import com.example.Betting.service.UserService;

/**
 * The Class TransactionController.
 */
@RestController
@RequestMapping("/api")
public class TransactionController {

	/** The transaction service. */
	@Autowired
	private TransactionService transactionService;

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Gets the transactions.
	 *
	 * @return the transactions
	 */
	@GetMapping("/transactions")
	ResponseEntity<?> getTransactions() {
		Collection<Transaction> allTransactions = transactionService.findAllTransactions();
		return ResponseEntity.status(HttpStatus.OK).body(allTransactions);
	}

	/**
     * Gets transactions of asked type.
     *
     * @param type the transaction type
     * @return the transactions of asked type
     */
    @GetMapping("/transactions/{type}")
    ResponseEntity<?> getTransactionsOfAskedType(@PathVariable final int type) {
        Collection<Transaction> transactions = transactionService.findAllTransactionTypes(type);
        return ResponseEntity.status(HttpStatus.OK).body(transactions);
    }

	/**
	 * Adds new transaction of type false.
	 * Sets the money value in wallet.
	 * Adding new money to wallet.
	 *
	 * @param transaction the transaction
	 * @return the response entity
	 * @throws URISyntaxException the URI syntax exception
	 */
	@PostMapping(value = "/transaction", consumes = "application/json")
	ResponseEntity<?> setMoneyInWallet(
	        @Valid @RequestBody final Transaction transaction)
	        throws URISyntaxException {

		long userId = transaction.getUser().getId();
		userService.changeMoneyValueInWallet(userId, transaction.getMoney(), true);

		if (transactionService.createTransaction(transaction, 0)) {
	        return ResponseEntity.ok().body("Successfully added new money to the wallet!");
		} else {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("You have to add at least 1 HRK to your account.");
		}

	}
}
