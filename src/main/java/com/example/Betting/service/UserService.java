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
public class UserService implements IUserService {

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
     * @param user the user
     * @param transactionMoney the transaction money
     * @param adding true for adding money, false for not adding money
     * @return the  user
     */
    public User changeMoneyValueInWallet(final User user, final float transactionMoney, final boolean adding) {
		float moneyInWallet = (float) user.getMoney();
		float newMoneyValue;

		if (adding) {
			newMoneyValue = transactionMoney + moneyInWallet;
		} else {
			newMoneyValue = moneyInWallet - transactionMoney;
		}

		user.setMoney(newMoneyValue);
		userRepository.save(user);
		return user;
    }

    /**
     * Check if enough money is in wallet for new bet.
     *
     * @param spentMoney money that user is planning to bet on a ticket.
     * @param user User who is placing a bet
     * @return the user
     */
    public boolean checkEnoughMoneyInWallet(final float spentMoney, final User user) {
        if (spentMoney > user.getMoney()) {
            return false;
        }
        return true;
    }

}
