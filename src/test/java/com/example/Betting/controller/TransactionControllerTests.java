package com.example.Betting.controller;

import static org.mockito.BDDMockito.given;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.Betting.model.Transaction;
import com.example.Betting.model.User;
import com.example.Betting.service.TransactionService;
import com.example.Betting.service.UserService;
import com.example.Betting.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * The Class TransactionControllerTests.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTests {

    /** The mvc. */
    @Autowired
    private MockMvc mvc;

    /** The transaction service. */
    @MockBean
    private TransactionService transactionService;

    /** The user service. */
    @MockBean
    private UserService userService;

    /**
     * When get transactions then array of transactions.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetTransactions_thenArrayOfTransactions()
      throws Exception {

    	Collection<Transaction> transactions = new ArrayList<Transaction>();
    	transactions.add(new Transaction((long) 1, Instant.parse("2025-02-15T17:00:00Z"), Constants.TYPE_ADDING, 300, 30, 5, null, null));
    	transactions.add(new Transaction((long) 2, Instant.parse("2025-02-15T17:00:00Z"), Constants.TYPE_TICKET_PAYMENT, 40, 10, 4, null, null));
    	given(transactionService.findAllTransactions()).willReturn(transactions);

        mvc.perform(MockMvcRequestBuilders.get("/api/transactions")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
                  "    {\r\n" + 
                  "        \"id\": 1,\r\n" + 
                  "        \"transactiondate\": 1739638800.000000000,\r\n" + 
                  "        \"transactiontype\": 0,\r\n" + 
                  "        \"money\": 300.0,\r\n" + 
                  "        \"taxes\": 30.0,\r\n" + 
                  "        \"manipulativespends\": 5.0,\r\n" + 
                  "        \"user\": null,\r\n" + 
                  "        \"ticket\": null\r\n" + 
                  "    },\r\n" + 
                  "    {\r\n" + 
                  "        \"id\": 2,\r\n" + 
                  "        \"transactiondate\": 1739638800.000000000,\r\n" + 
                  "        \"transactiontype\": 1,\r\n" + 
                  "        \"money\": 40.0,\r\n" + 
                  "        \"taxes\": 10.0,\r\n" + 
                  "        \"manipulativespends\": 4.0,\r\n" + 
                  "        \"user\": null,\r\n" + 
                  "        \"ticket\": null\r\n" + 
                  "    }\r\n" + 
                  "]"));
    }

    /**
     * When get transactions then transacton.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetTransactions_thenTransacton()
      throws Exception {

    	Collection<Transaction> transactions = new ArrayList<Transaction>();
    	transactions.add(new Transaction((long) 1, Instant.parse("2022-05-14T17:00:00Z"), Constants.TYPE_ADDING, 300, 30, 5, null, null));
    	given(transactionService.findAllTransactions()).willReturn(transactions);

        mvc.perform(MockMvcRequestBuilders.get("/api/transactions")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
                  "    {\r\n" + 
                  "        \"id\": 1,\r\n" + 
                  "        \"transactiondate\": 1652547600.000000000,\r\n" + 
                  "        \"transactiontype\": 0,\r\n" + 
                  "        \"money\": 300.0,\r\n" + 
                  "        \"taxes\": 30.0,\r\n" + 
                  "        \"manipulativespends\": 5.0,\r\n" + 
                  "        \"user\": null,\r\n" + 
                  "        \"ticket\": null\r\n" + 
                  "    }\r\n" + 
                  "]"));
    }

    /**
     * When get transactions then no transactions.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetTransactions_thenNoTransactions()
      throws Exception {

    	given(transactionService.findAllTransactions()).willReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/api/transactions")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    /**
     * Given transaction when set money in wallet then created and transaction.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenTransaction_whenSetMoneyInWalletWithWrongUser_thenBadRequest()
      throws Exception {

        User initialUser = new User(1, "John Doe", "Split", 24, 600, null);
        Transaction newTransaction = new Transaction((long) 1, Instant.parse("2022-05-13T17:00:00Z"), Constants.TYPE_ADDING, 300, 0, 0, initialUser, null);

        given(userService.changeMoneyValueInWallet(initialUser, 300, true)).willReturn(initialUser);
        given(transactionService.createTransaction(300, null, initialUser, 0)).willReturn(true);

        mvc.perform(MockMvcRequestBuilders.post("/api/transaction")
          .content(asJsonString(newTransaction))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("Invalid user!"));
    }

    /**
     * Given transaction when set money in wallet then created and transaction.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenTransaction_whenSetMoneyInWallet_thenCreatedAndTransaction()
      throws Exception {

    	User initialUser = new User(1, "John Doe", "Split", 24, 600, null);
        Transaction newTransaction = new Transaction((long) 1, Instant.parse("2022-05-13T17:00:00Z"), Constants.TYPE_ADDING, 300, 0, 0, initialUser, null);

    	given(userService.changeMoneyValueInWallet(initialUser, 300, true)).willReturn(initialUser);
    	given(transactionService.createTransaction(300, null, initialUser, 0)).willReturn(true);
    	given(userService.findUserById(1L)).willReturn(Optional.of(initialUser));

    	mvc.perform(MockMvcRequestBuilders.post("/api/transaction")
          .content(asJsonString(newTransaction))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().string("Successfully added new money to the wallet!"));
    }

    /**
     * Given transaction with enough money value when set money in wallet then created and transaction.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenTransactionWithEnoughMoneyValue_whenSetMoneyInWallet_thenCreatedAndTransaction()
      throws Exception {

    	User initialUser = new User(1, "John Doe", "Split", 24, 301, null);
        Transaction newTransaction = new Transaction((long) 1, Instant.parse("2022-05-12T17:00:00Z"), Constants.TYPE_ADDING, 1, 0, 0, initialUser, null);

    	given(userService.changeMoneyValueInWallet(initialUser, 1, true)).willReturn(initialUser);
    	given(transactionService.createTransaction(1, null, initialUser, 0)).willReturn(true);
        given(userService.findUserById(1L)).willReturn(Optional.of(initialUser));

    	mvc.perform(MockMvcRequestBuilders.post("/api/transaction")
          .content(asJsonString(newTransaction))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().string("Successfully added new money to the wallet!"));
    }

    /**
     * Given transaction with low money value when set money in wallet then bad request.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenTransactionWithLowMoneyValue_whenSetMoneyInWallet_thenBadRequest()
      throws Exception {

    	User initialUser = new User(1, "John Doe", "Split", 24, 300, null);
        Transaction newTransaction = new Transaction((long) 1, Instant.parse("2022-05-11T17:00:00Z"), Constants.TYPE_ADDING, 0, 0, 0, initialUser, null);

        given(userService.findUserById(1L)).willReturn(Optional.of(initialUser));

    	mvc.perform(MockMvcRequestBuilders.post("/api/transaction")
          .content(asJsonString(newTransaction))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You have to add at least 1 HRK to your account."));
    }

    /**
     * Given transaction with negative money value when set money in wallet then bad request.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenTransactionWithNegativeMoneyValue_whenSetMoneyInWallet_thenBadRequest()
      throws Exception {

    	User initialUser = new User(1, "John Doe", "Split", 24, 300, null);
        Transaction newTransaction = new Transaction((long) 1, Instant.parse("2022-05-10T17:00:00Z"), Constants.TYPE_ADDING, 0, 0, 0, initialUser, null);
        given(userService.findUserById(1L)).willReturn(Optional.of(initialUser));
        
    	mvc.perform(MockMvcRequestBuilders.post("/api/transaction")
          .content(asJsonString(newTransaction))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You have to add at least 1 HRK to your account."));
    }

    /**
     * As json string.
     *
     * @param transaction the transaction
     * @return the string
     */
    public static String asJsonString(final Transaction transaction) {
      try {
          final ObjectMapper mapper = new ObjectMapper();
          JavaTimeModule module = new JavaTimeModule();
          mapper.registerModule(module);
          mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
          final String jsonContent = mapper.writeValueAsString(transaction);
          return jsonContent;
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
    }

}
