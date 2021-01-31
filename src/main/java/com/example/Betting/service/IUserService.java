package com.example.Betting.service;

import java.util.Optional;

import com.example.Betting.model.User;

/**
 * The Interface IUserService.
 */
public interface IUserService {

    /**
     * Find user by id.
     *
     * @param id the id
     * @return the optional user
     */
    Optional<User> findUserById(Long id);

    /**
     * Change money value in wallet.
     *
     * @param user the user
     * @param transactionMoney the transaction money
     * @param adding the adding money value
     * @return the  user
     */
    User changeMoneyValueInWallet(User user, float transactionMoney, boolean adding);

    /**
     * Check if enough money is in wallet for new bet.
     *
     * @param spentMoney money that user is planning to bet on a ticket.
     * @param user User who is placing a bet
     * @return the user
     */
    boolean checkMoneyInWallet(float spentMoney, User user);

}
