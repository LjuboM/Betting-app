package com.example.Betting.controller;

import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.Transaction;
import com.example.Betting.model.User;
import com.example.Betting.repository.TransactionRepository;
import com.example.Betting.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class TransactionController {
	private TransactionRepository transactionRepository;
	private UserRepository userRepository;
	
	public TransactionController(TransactionRepository transactionRepository, UserRepository userRepository) {
		super();
		this.transactionRepository = transactionRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("/transactions")
	Collection<Transaction> transactions(){
		return transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "transdate"));
			}

	//Add money to account: Add new transaction of type false and increment User's money.
	@PostMapping(value="/transaction", consumes="application/json")
	ResponseEntity<String> addMoneyToWallet(@Valid @RequestBody Transaction transaction) throws URISyntaxException{
		if(transaction.getMoney() < 1.0) {
	        return ResponseEntity.badRequest().body("You have to add at least 1 HRK to your account.");
		}
		else {
			//Get User who added money to wallet.
			Optional<User> user = userRepository.findById(transaction.getUser().getId());
			float moneyInWallet = user.get().getMoney();
			float addedMoney = transaction.getMoney();

			//Increment money.
			float incrementedMoney = addedMoney + moneyInWallet;
			user.get().setMoney(incrementedMoney);
			userRepository.save(user.get());

			//Set transaction to current time.
			transaction.setTransdate(Instant.now());
			transactionRepository.save(transaction);
			return ResponseEntity.ok("Succesfully added " + String.valueOf(addedMoney) + " HRK to your wallet.\nNow You have " + String.valueOf(incrementedMoney) + " HRK on your account.");
		}
	}
}
