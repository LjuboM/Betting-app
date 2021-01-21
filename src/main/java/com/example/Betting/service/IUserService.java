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
     * @param id the id
     * @param transactionMoney the transaction money
     * @param adding the adding money value
     * @return the optional user
     */
    Optional<User> changeMoneyValueInWallet(Long id, int transactionMoney, boolean adding);
}
