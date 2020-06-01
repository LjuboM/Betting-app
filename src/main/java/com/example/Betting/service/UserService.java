package com.example.Betting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Betting.model.User;
import com.example.Betting.repository.UserRepository;

/**
 * The Class UserService.
 */
@Service
public class UserService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

    /**
     * Find user by id.
     *
     * @param id the id
     * @return the optional user
     */
    public Optional<User> findUserById(final Long id) {

        return userRepository.findById(id);
    }

    /**
     * Change money value in wallet.
     *
     * @param id the id
     * @param transactionMoney the transaction money
     * @param adding the adding
     * @return the optional user
     */
    public Optional<User> changeMoneyValueInWallet(final Long id, final int transactionMoney, final boolean adding) {
    	Optional<User> user = findUserById(id);
		int moneyInWallet = user.get().getMoney();
		int newMoneyValue;

		if (adding) {
			newMoneyValue = transactionMoney + (int) moneyInWallet;
		} else {
			newMoneyValue = (int) moneyInWallet - transactionMoney;
		}

		user.get().setMoney(newMoneyValue);
		userRepository.save(user.get());
		return user;
    }

}
