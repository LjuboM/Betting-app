package com.example.Betting.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Betting.model.Ticket;
import com.example.Betting.model.User;
import com.example.Betting.repository.TransactionRepository;
import com.example.Betting.utils.Constants;

/**
 * The Class TransactionServiceTests.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionService.class)
public class TransactionServiceTests {

    /** The transaction repository. */
    @MockBean
    private TransactionRepository transactionRepository;

    /** The user service. */
    @Autowired
    private TransactionService transactionService;

    /**
     * Given money 1, ticket, user and transaction type 0 when creating transaction then true.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenMoneyTicketUserAndTransactionType0_whencreateTransaction_thenTrue()
      throws Exception {

        boolean result = false;
        float money = 1f;
        int transactionType = 0;
        User user = new User(1, "John Doe", "Split", 24, 500, null);
        Ticket ticket = new Ticket((long) 1, 4, 684, Constants.STATUS_WAITING, null, null);
        result = transactionService.createTransaction(money, ticket, user, transactionType);

        assertEquals(true, result);
    }

    /**
     * Given money less than 1 when creating transaction then false.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenLessThan1Money_whencreateTransaction_thenFalse()
      throws Exception {

        boolean result = false;
        float money = 0.9f;
        int transactionType = 0;
        User user = new User(1, "John Doe", "Split", 24, 500, null);
        Ticket ticket = new Ticket((long) 1, 4, 684, Constants.STATUS_WAITING, null, null);
        result = transactionService.createTransaction(money, ticket, user, transactionType);

        assertEquals(false, result);
    }

    /**
     * Given money 1, ticket, user and transaction type 1 when creating transaction then true.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenMoneyTicketUserAndTransactionType1_whencreateTransaction_thenTrue()
      throws Exception {

        boolean result = false;
        float money = 1f;
        int transactionType = 1;
        User user = new User(1, "John Doe", "Split", 24, 500, null);
        Ticket ticket = new Ticket((long) 1, 4, 684, Constants.STATUS_WAITING, null, null);
        result = transactionService.createTransaction(money, ticket, user, transactionType);

        assertEquals(true, result);
    }
}
