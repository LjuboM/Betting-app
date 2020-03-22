package com.example.Betting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Betting.model.User;
import com.example.Betting.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
    public Optional<User> findUserById(Long id) {

        return userRepository.findById(id);
    }
    
    public Optional<User> changeMoneyValueInWallet(Long id, int transactionMoney, boolean adding){
    	Optional<User> user = findUserById(id);
		int moneyInWallet = user.get().getMoney();
		int newMoneyValue;
		//Increment money.
		if(adding) {
			newMoneyValue = transactionMoney + (int) moneyInWallet;
		}
		else {
			newMoneyValue = (int) moneyInWallet - transactionMoney;
		}
		
		user.get().setMoney(newMoneyValue);
		userRepository.save(user.get());
		return user;
    }
    
}
