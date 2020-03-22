package com.example.Betting;

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

import com.example.Betting.controller.TransactionController;
import com.example.Betting.model.Transaction;
import com.example.Betting.model.User;
import com.example.Betting.service.TransactionService;
import com.example.Betting.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTests {
 
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private TransactionService transactionService;
    
    @MockBean
    private UserService userService;
 
    @Test
    public void whenGetTransactions_thenArrayOfTransactions()
      throws Exception {
        
    	Collection<Transaction> transactions = new ArrayList<Transaction>();
    	transactions.add(new Transaction((long) 1, Instant.parse("2020-05-15T17:00:00Z"), false, 300, null, null));
    	transactions.add(new Transaction((long) 2, Instant.parse("2020-05-16T17:00:00Z"), true, 40, null, null));
    	given(transactionService.findAllTransactions()).willReturn(transactions);

        mvc.perform(MockMvcRequestBuilders.get("/api/transactions")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
          		"    {\r\n" + 
          		"        \"id\": 1,\r\n" + 
          		"        \"transactiondate\": \"2020-05-15T17:00:00Z\",\r\n" + 
          		"        \"transactiontype\": false,\r\n" + 
          		"        \"money\": 300,\r\n" + 
          		"        \"user\": null\r\n" + 
          		"    },\r\n" + 
          		"    {\r\n" + 
          		"        \"id\": 2,\r\n" + 
          		"        \"transactiondate\": \"2020-05-16T17:00:00Z\",\r\n" + 
          		"        \"transactiontype\": true,\r\n" + 
          		"        \"money\": 40,\r\n" + 
          		"        \"user\": null\r\n" + 
          		"    }\r\n" + 
          		"]"));
    }
    
    @Test
    public void whenGetTransactions_thenTransacton()
      throws Exception {
        
    	Collection<Transaction> transactions = new ArrayList<Transaction>();
    	transactions.add(new Transaction((long) 1, Instant.parse("2020-05-15T17:00:00Z"), false, 300, null, null));
    	given(transactionService.findAllTransactions()).willReturn(transactions);

        mvc.perform(MockMvcRequestBuilders.get("/api/transactions")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
          		"    {\r\n" + 
          		"        \"id\": 1,\r\n" + 
          		"        \"transactiondate\": \"2020-05-15T17:00:00Z\",\r\n" + 
          		"        \"transactiontype\": false,\r\n" + 
          		"        \"money\": 300,\r\n" + 
          		"        \"user\": null\r\n" + 
          		"    }\r\n" + 
          		"]"));
    }
    
    @Test
    public void whenGetTransactions_thenNoTransactions()
      throws Exception {
        
    	given(transactionService.findAllTransactions()).willReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/api/transactions")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[]"));
    }
    
    
    @Test
    public void givenTransaction_whenSetMoneyInWallet_thenCreatedAndTransaction()
      throws Exception {
    	
    	User ljubo = new User(1, "Ljubo Mamic", "Split", 24, 600, null);
        Transaction newTransaction = new Transaction((long) 1, Instant.parse("2020-05-15T17:00:00Z"), false, 300, ljubo, null);
        
    	given(userService.changeMoneyValueInWallet((long) 1, 300, true)).willReturn(Optional.of(ljubo));
    	given(transactionService.createTransaction(newTransaction, false)).willReturn(newTransaction);


    	mvc.perform(MockMvcRequestBuilders.post("/api/transaction")
          .content(asJsonString(newTransaction))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isCreated())
          .andExpect(MockMvcResultMatchers.content().json("{\r\n" + 
          		"    \"id\": 1,\r\n" + 
          		"    \"transactiondate\": \"2020-05-15T17:00:00Z\",\r\n" +
          		"    \"transactiontype\": false,\r\n" + 
          		"    \"money\": 300,\r\n" + 
          		"    \"user\": {\r\n" + 
          		"        \"id\": 1,\r\n" + 
          		"        \"name\": \"Ljubo Mamic\",\r\n" + 
          		"        \"location\": \"Split\",\r\n" + 
          		"        \"age\": 24,\r\n" + 
          		"        \"money\": 600\r\n" + 
          		"    }\r\n" + 
          		"}"));
    }

    @Test
    public void givenTransactionWithEnoughMoneyValue_whenSetMoneyInWallet_thenCreatedAndTransaction()
      throws Exception {
    	
    	User ljubo = new User(1, "Ljubo Mamic", "Split", 24, 301, null);
        Transaction newTransaction = new Transaction((long) 1, Instant.parse("2020-05-15T17:00:00Z"), false, 1, ljubo, null);
        
    	given(userService.changeMoneyValueInWallet((long) 1, 1, true)).willReturn(Optional.of(ljubo));
    	given(transactionService.createTransaction(newTransaction, false)).willReturn(newTransaction);


    	mvc.perform(MockMvcRequestBuilders.post("/api/transaction")
          .content(asJsonString(newTransaction))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isCreated())
          .andExpect(MockMvcResultMatchers.content().json("{\r\n" + 
          		"    \"id\": 1,\r\n" + 
          		"    \"transactiondate\": \"2020-05-15T17:00:00Z\",\r\n" +
          		"    \"transactiontype\": false,\r\n" + 
          		"    \"money\": 1,\r\n" + 
          		"    \"user\": {\r\n" + 
          		"        \"id\": 1,\r\n" + 
          		"        \"name\": \"Ljubo Mamic\",\r\n" + 
          		"        \"location\": \"Split\",\r\n" + 
          		"        \"age\": 24,\r\n" + 
          		"        \"money\": 301\r\n" + 
          		"    }\r\n" + 
          		"}"));
    }
    
    @Test
    public void givenTransactionWithLowMoneyValue_whenSetMoneyInWallet_thenBadRequest()
      throws Exception {
    	
    	User ljubo = new User(1, "Ljubo Mamic", "Split", 24, 300, null);
        Transaction newTransaction = new Transaction((long) 1, Instant.parse("2020-05-15T17:00:00Z"), false, 0, ljubo, null);

    	mvc.perform(MockMvcRequestBuilders.post("/api/transaction")
          .content(asJsonString(newTransaction))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You have to add at least 1 HRK to your account."));
    }
    
    @Test
    public void givenTransactionWithNegativeMoneyValue_whenSetMoneyInWallet_thenBadRequest()
      throws Exception {
    	
    	User ljubo = new User(1, "Ljubo Mamic", "Split", 24, 300, null);
        Transaction newTransaction = new Transaction((long) -1, Instant.parse("2020-05-15T17:00:00Z"), false, 0, ljubo, null);

    	mvc.perform(MockMvcRequestBuilders.post("/api/transaction")
          .content(asJsonString(newTransaction))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You have to add at least 1 HRK to your account."));
    }
    
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