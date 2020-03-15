package com.example.Betting;

import static org.mockito.BDDMockito.given;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
import com.example.Betting.service.TransactionService;
import com.example.Betting.service.UserService;

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
    	transactions.add(new Transaction((long) 1, Instant.parse("2020-05-15T17:00:00Z"), false, (float) 300, null, null ));
    	transactions.add(new Transaction((long) 2, Instant.parse("2020-05-16T17:00:00Z"), true, (float) 40, null, null ));
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
    public void whenGetTransactions_thenTransaction()
      throws Exception {
        
    	Collection<Transaction> transactions = new ArrayList<Transaction>();
    	transactions.add(new Transaction((long) 1, Instant.parse("2020-05-15T17:00:00Z"), false, (float) 300, null, null ));
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
}