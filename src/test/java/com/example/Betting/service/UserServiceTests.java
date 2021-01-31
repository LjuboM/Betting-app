package com.example.Betting.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Betting.model.User;
import com.example.Betting.repository.UserRepository;

/**
 * The Class UserServiceTests.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserService.class)
public class UserServiceTests {

    /** The user repository. */
    @MockBean
    private UserRepository userRepository;

    /** The user service. */
    @Autowired
    private UserService userService;

    /**
     * Given adding money when change money value in wallet then add money.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenAddingMoney_whenchangeMoneyValueInWallet_thenAddMoney()
      throws Exception {

        User initialUser = new User(1, "John Doe", "Split", 24, 500, null);
        User changedUser = new User(1, "John Doe", "Split", 24, 625, null);

        given(userRepository.findById(initialUser.getId())).willReturn(Optional.of(initialUser));
        given(userRepository.save(initialUser)).willReturn(changedUser);

        User expectedUser = userService.changeMoneyValueInWallet(initialUser, 125, true);
        assertEquals(changedUser, expectedUser);
    }
 
    /**
     * Given removing money when change money value in wallet then remove money.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenRemovingMoney_whenchangeMoneyValueInWallet_thenRemoveMoney()
      throws Exception {

        User initialUser = new User(1, "John Doe", "Split", 24, 500, null);
        User changedUser = new User(1, "John Doe", "Split", 24, 375, null);

        given(userRepository.findById(initialUser.getId())).willReturn(Optional.of(initialUser));
        given(userRepository.save(initialUser)).willReturn(changedUser);

        User expectedUser = userService.changeMoneyValueInWallet(initialUser, 125, false);
        assertEquals(changedUser, expectedUser);
    }

    /**
     * Given adding 0 money when change money value in wallet then add zero money.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenAddingZeroMoney_whenchangeMoneyValueInWallet_thenAddZeroMoney()
      throws Exception {

        User initialUser = new User(1, "John Doe", "Split", 24, 500, null);
        User changedUser = new User(1, "John Doe", "Split", 24, 500, null);

        given(userRepository.findById(initialUser.getId())).willReturn(Optional.of(initialUser));
        given(userRepository.save(initialUser)).willReturn(changedUser);

        User expectedUser = userService.changeMoneyValueInWallet(initialUser, 0, true);
        assertEquals(changedUser, expectedUser);
    }

    /**
     * Given removing 0 money when change money value in wallet then remove zero money.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenRemovingZeroMoney_whenchangeMoneyValueInWallet_thenRemoveZeroMoney()
      throws Exception {

        User initialUser = new User(1, "John Doe", "Split", 24, 500, null);
        User changedUser = new User(1, "John Doe", "Split", 24, 500, null);

        given(userRepository.findById(initialUser.getId())).willReturn(Optional.of(initialUser));
        given(userRepository.save(initialUser)).willReturn(changedUser);

        User expectedUser = userService.changeMoneyValueInWallet(initialUser, 0, false);
        assertEquals(changedUser, expectedUser);
    }
}
