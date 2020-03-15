package com.example.Betting.controller;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.Transaction;
import com.example.Betting.model.User;
import com.example.Betting.service.TransactionService;
import com.example.Betting.service.UserService;

@RestController
@RequestMapping("/api")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	UserService userService;

	@GetMapping("/transactions")
	Collection<Transaction> transactions(){
		return transactionService.findAllTransactions();
			}

	//Add money to account: Add new transaction of type false and increment User's money.
	@PostMapping(value="/transaction", consumes="application/json")
	ResponseEntity<?> setMoneyInWallet(@Valid @RequestBody Transaction transaction) throws URISyntaxException{
		if(transaction.getMoney() < 1.0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You have to add at least 1 HRK to your account.");
		}
		else {
			//Get User who added money to wallet.
			Optional<User> user = userService.changeMoneyValueInWallet(transaction.getUser().getId(), transaction.getMoney(), true);
			Transaction newTransaction = transactionService.createTransaction(transaction, false);
			newTransaction.setUser(user.get());
			return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
		}
	}
}
