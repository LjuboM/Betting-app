package com.example.Betting.service;

import java.util.Optional;

import com.example.Betting.model.User;

public interface IUserService {
	
    Optional<User> findUserById(Long id);
    
    Optional<User> changeMoneyValueInWallet(Long id, float transactionMoney, boolean adding);
}
