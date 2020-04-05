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

import com.example.Betting.controller.TicketOddsController;
import com.example.Betting.model.Match;
import com.example.Betting.model.Odds;
import com.example.Betting.model.Ticket;
import com.example.Betting.model.TicketOdds;
import com.example.Betting.model.Transaction;
import com.example.Betting.model.User;
import com.example.Betting.service.TicketOddsService;
import com.example.Betting.service.TransactionService;
import com.example.Betting.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(SpringRunner.class)
@WebMvcTest(TicketOddsController.class)
public class TicketOddsControllerTests {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private TicketOddsService ticketOddsService;
    
    @MockBean
    private UserService userService;
    
    @MockBean
    private TransactionService transactionService;
    
    @Test
    public void whenGetTicketOdds_thenArrayOfTicketOdds()
      throws Exception {
        
    	Ticket ticket = new Ticket((long) 1, 4, 400, null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, null, null);
    	Odds secondOdd = new Odds((long) 1, "Basic", 2, 2, 2, 2, 3, 3, null, null);
    	
    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));
    	given(ticketOddsService.findAllPlayedTickets()).willReturn(ticketOdds);

        mvc.perform(MockMvcRequestBuilders.get("/api/ticketOdds")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
          		"    {\r\n" + 
          		"        \"@id\": 1,\r\n" + 
          		"        \"id\": 1,\r\n" + 
          		"        \"ticket\": {\r\n" + 
          		"            \"id\": 1,\r\n" + 
          		"            \"totalodd\": 4,\r\n" + 
          		"            \"possiblegain\": 400,\r\n" + 
          		"            \"transaction\": null\r\n" + 
          		"        },\r\n" + 
          		"        \"odds\": {\r\n" + 
          		"            \"id\": 1,\r\n" + 
          		"            \"type\": \"Basic\",\r\n" + 
          		"            \"odd1\": 2,\r\n" + 
          		"            \"odd2\": 3,\r\n" + 
          		"            \"odd3\": 2,\r\n" + 
          		"            \"odd4\": 4,\r\n" + 
          		"            \"odd5\": 2,\r\n" + 
          		"            \"odd6\": 4,\r\n" + 
          		"            \"match\": null\r\n" + 
          		"        },\r\n" + 
          		"        \"odd\": 2,\r\n" + 
          		"        \"type\": \"1\"\r\n" + 
          		"    },\r\n" + 
          		"    {\r\n" + 
          		"        \"@id\": 2,\r\n" + 
          		"        \"id\": 2,\r\n" + 
          		"        \"ticket\": {\r\n" + 
          		"            \"id\": 1,\r\n" + 
          		"            \"totalodd\": 4,\r\n" + 
          		"            \"possiblegain\": 400,\r\n" + 
          		"            \"transaction\": null\r\n" + 
          		"        },\r\n" + 
          		"        \"odds\": {\r\n" + 
          		"            \"id\": 1,\r\n" + 
          		"            \"type\": \"Basic\",\r\n" + 
          		"            \"odd1\": 2,\r\n" + 
          		"            \"odd2\": 2,\r\n" + 
          		"            \"odd3\": 2,\r\n" + 
          		"            \"odd4\": 2,\r\n" + 
          		"            \"odd5\": 3,\r\n" + 
          		"            \"odd6\": 3,\r\n" + 
          		"            \"match\": null\r\n" + 
          		"        },\r\n" + 
          		"        \"odd\": 2,\r\n" + 
          		"        \"type\": \"X2\"\r\n" + 
          		"    }\r\n" + 
          		"]"));
    }
    
    @Test
    public void whenGetTicketOdds_thenTicketOdds()
      throws Exception {
        
    	Ticket ticket = new Ticket((long) 1, 4, 400, null, null);
    	Odds odd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, null, null);
    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, odd, (long) 2, "1"));

    	given(ticketOddsService.findAllPlayedTickets()).willReturn(ticketOdds);

        mvc.perform(MockMvcRequestBuilders.get("/api/ticketOdds")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
          		"    {\r\n" + 
          		"        \"@id\": 1,\r\n" + 
          		"        \"id\": 1,\r\n" + 
          		"        \"ticket\": {\r\n" +  
          		"            \"id\": 1,\r\n" + 
          		"            \"totalodd\": 4,\r\n" + 
          		"            \"possiblegain\": 400,\r\n" + 
          		"            \"transaction\": null\r\n" + 
          		"        },\r\n" + 
          		"        \"odds\": {\r\n" + 
          		"            \"id\": 1,\r\n" + 
          		"            \"type\": \"Basic\",\r\n" + 
          		"            \"odd1\": 2,\r\n" + 
          		"            \"odd2\": 3,\r\n" + 
          		"            \"odd3\": 2,\r\n" + 
          		"            \"odd4\": 4,\r\n" + 
          		"            \"odd5\": 2,\r\n" + 
          		"            \"odd6\": 4,\r\n" + 
          		"            \"match\": null\r\n" + 
          		"        },\r\n" + 
          		"        \"odd\": 2,\r\n" + 
          		"        \"type\": \"1\"\r\n" + 
          		"    }\r\n" + 
          		"]"));
    }
    
    @Test
    public void whenGetTicketOdds_thenNoTicketOdds()
      throws Exception {

    	given(ticketOddsService.findAllPlayedTickets()).willReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/api/ticketOdds")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[]"));
    }
    
    @Test
    public void whenGetMatchPairsByTicketId_thenArrayOfTicketOdds()
        throws Exception {
    	
    	long ticketId = 1;
    	
    	Ticket ticket = new Ticket((long) 1, 4, 400, null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, null, null);
    	Odds secondOdd = new Odds((long) 1, "Basic", 2, 2, 2, 2, 3, 3, null, null);
    	
    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));
    	
        given(ticketOddsService.findAllPlayedPairsByTicketId(ticketId)).willReturn(ticketOdds);

        mvc.perform(MockMvcRequestBuilders.get("/api/ticketOdds/{ticketId}", ticketId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
              		"    {\r\n" + 
              		"        \"@id\": 1,\r\n" + 
              		"        \"id\": 1,\r\n" + 
              		"        \"ticket\": {\r\n" + 
              		"            \"id\": 1,\r\n" + 
              		"            \"totalodd\": 4,\r\n" + 
              		"            \"possiblegain\": 400,\r\n" + 
              		"            \"transaction\": null\r\n" + 
              		"        },\r\n" + 
              		"        \"odds\": {\r\n" + 
              		"            \"id\": 1,\r\n" + 
              		"            \"type\": \"Basic\",\r\n" + 
              		"            \"odd1\": 2,\r\n" + 
              		"            \"odd2\": 3,\r\n" + 
              		"            \"odd3\": 2,\r\n" + 
              		"            \"odd4\": 4,\r\n" + 
              		"            \"odd5\": 2,\r\n" + 
              		"            \"odd6\": 4,\r\n" + 
              		"            \"match\": null\r\n" + 
              		"        },\r\n" + 
              		"        \"odd\": 2,\r\n" + 
              		"        \"type\": \"1\"\r\n" + 
              		"    },\r\n" + 
              		"    {\r\n" + 
              		"        \"@id\": 2,\r\n" + 
              		"        \"id\": 2,\r\n" + 
              		"        \"ticket\": {\r\n" + 
              		"            \"id\": 1,\r\n" + 
              		"            \"totalodd\": 4,\r\n" + 
              		"            \"possiblegain\": 400,\r\n" + 
              		"            \"transaction\": null\r\n" + 
              		"        },\r\n" + 
              		"        \"odds\": {\r\n" + 
              		"            \"id\": 1,\r\n" + 
              		"            \"type\": \"Basic\",\r\n" + 
              		"            \"odd1\": 2,\r\n" + 
              		"            \"odd2\": 2,\r\n" + 
              		"            \"odd3\": 2,\r\n" + 
              		"            \"odd4\": 2,\r\n" + 
              		"            \"odd5\": 3,\r\n" + 
              		"            \"odd6\": 3,\r\n" + 
              		"            \"match\": null\r\n" + 
              		"        },\r\n" + 
              		"        \"odd\": 2,\r\n" + 
              		"        \"type\": \"X2\"\r\n" + 
              		"    }\r\n" + 
              		"]"));
        } 
    
    @Test
    public void whenGetMatchPairsByTicketId_thenTicketOdds()
        throws Exception {
    	
    	long ticketId = 1;
    	Ticket ticket = new Ticket((long) 1, 4, 400, null, null);
    	Odds odd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, null, null);
    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, odd, (long) 2, "1"));
    	
        given(ticketOddsService.findAllPlayedPairsByTicketId(ticketId)).willReturn(ticketOdds);

        mvc.perform(MockMvcRequestBuilders.get("/api/ticketOdds/{ticketId}", ticketId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
              		"    {\r\n" + 
              		"        \"@id\": 1,\r\n" + 
              		"        \"id\": 1,\r\n" + 
              		"        \"ticket\": {\r\n" +  
              		"            \"id\": 1,\r\n" + 
              		"            \"totalodd\": 4,\r\n" + 
              		"            \"possiblegain\": 400,\r\n" + 
              		"            \"transaction\": null\r\n" + 
              		"        },\r\n" + 
              		"        \"odds\": {\r\n" + 
              		"            \"id\": 1,\r\n" + 
              		"            \"type\": \"Basic\",\r\n" + 
              		"            \"odd1\": 2,\r\n" + 
              		"            \"odd2\": 3,\r\n" + 
              		"            \"odd3\": 2,\r\n" + 
              		"            \"odd4\": 4,\r\n" + 
              		"            \"odd5\": 2,\r\n" + 
              		"            \"odd6\": 4,\r\n" + 
              		"            \"match\": null\r\n" + 
              		"        },\r\n" + 
              		"        \"odd\": 2,\r\n" + 
              		"        \"type\": \"1\"\r\n" + 
              		"    }\r\n" + 
              		"]"));
        } 
    
    @Test
    public void whenGetMatchPairsByTicketId_thenNoTicketOdds()
        throws Exception {
    	
    	long ticketId = 1;
        given(ticketOddsService.findAllPlayedPairsByTicketId(ticketId)).willReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/api/ticketOdds/{ticketId}", ticketId)
           .contentType(MediaType.APPLICATION_JSON))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.content().json("[]"));
        }
        
    @Test
    public void givenArrayOfBasicTicketOdds_whenPlayingTicket_thenOk()
      throws Exception {
        int moneyInWalletBefore = 200;
        int spentMoney = 200;
        Instant matchDate = Instant.now().plusSeconds(60);
        
    	User ljuboBeforeBet = new User(1, "Ljubo Mamic", "Split", 24, moneyInWalletBefore, null);
    	User ljuboAfterBet = new User(1, "Ljubo Mamic", "Split", 24, moneyInWalletBefore - spentMoney, null);
        Transaction newTransaction = new Transaction((long) 1, null, true,  spentMoney, ljuboAfterBet, null);
        
        given(userService.findUserById((long) 1)).willReturn(Optional.of(ljuboBeforeBet));
    	given(userService.changeMoneyValueInWallet((long) 1, 200, false)).willReturn(Optional.of(ljuboAfterBet));
    	given(transactionService.createTransaction(newTransaction, true)).willReturn(newTransaction);

    	Ticket ticket = new Ticket((long) 1, 4, 800, newTransaction, null);
    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
    	Odds secondOdd = new Odds((long) 2, "Basic", 2, 2, 2, 2, 3, 3, secondMatch, null);

    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));
    	
        mvc.perform(MockMvcRequestBuilders.post("/api/ticket")
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().string("Successfully placed a bet!"));
    }
    
    @Test
    public void givenPairOfBasicTicketOdds_whenPlayingTicket_thenOk()
      throws Exception {
    	
        int moneyInWalletBefore = 400;
        int spentMoney = 200;
        Instant matchDate = Instant.now().plusSeconds(60);

    	User ljuboBeforeBet = new User(1, "Ljubo Mamic", "Split", 24, moneyInWalletBefore, null);
    	User ljuboAfterBet = new User(1, "Ljubo Mamic", "Split", 24, moneyInWalletBefore - spentMoney, null);
        Transaction newTransaction = new Transaction((long) 1, null, true,  spentMoney, ljuboAfterBet, null);
        
        given(userService.findUserById((long) 1)).willReturn(Optional.of(ljuboBeforeBet));
    	given(userService.changeMoneyValueInWallet((long) 1, 200, false)).willReturn(Optional.of(ljuboAfterBet));
    	given(transactionService.createTransaction(newTransaction, true)).willReturn(newTransaction);

    	Ticket ticket = new Ticket((long) 1, 4, 800, newTransaction, null);
    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);

    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	
        mvc.perform(MockMvcRequestBuilders.post("/api/ticket")
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().string("Successfully placed a bet!"));
    }
    
    @Test
    public void givenArrayOfTicketOddsWithSpecialOffer_whenPlayingTicketWithEnoughBiggerOdds_thenOk()
      throws Exception {
    	
        int moneyInWalletBefore = 200;
        int spentMoney = 200;
        Instant matchDate = Instant.now().plusSeconds(60);
        
    	User ljuboBeforeBet = new User(1, "Ljubo Mamic", "Split", 24, moneyInWalletBefore, null);
    	User ljuboAfterBet = new User(1, "Ljubo Mamic", "Split", 24, moneyInWalletBefore - spentMoney, null);
        Transaction newTransaction = new Transaction((long) 1, null, true,  spentMoney, ljuboAfterBet, null);
        
        given(userService.findUserById((long) 1)).willReturn(Optional.of(ljuboBeforeBet));
    	given(userService.changeMoneyValueInWallet((long) 1, 200, false)).willReturn(Optional.of(ljuboAfterBet));
    	given(transactionService.createTransaction(newTransaction, true)).willReturn(newTransaction);

    	Ticket ticket = new Ticket((long) 1, 4, 800, newTransaction, null);
    	
    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", null, null);
    	Match thirdMatch = new Match((long) 3, matchDate, "Rogger Federer", "Rafael Nadal", null, null);
    	Match fourthMatch = new Match((long) 4, matchDate, "Hajduk", "Dinamo", null, null);
    	Match fifthMatch = new Match((long) 5, matchDate, "Rijeka", "Osijek", null, null);
    	Match sixthMatch = new Match((long) 6, matchDate, "Solin", "Kastela", null, null);
    	
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
    	Odds secondOdd = new Odds((long) 2, "Special Offer", 2, 2, 2, 2, 3, 3, secondMatch, null);
    	Odds thirdOdd = new Odds((long) 3, "Basic", 2, 2, 2, 2, 3, 3, thirdMatch, null);
    	Odds fourthOdd = new Odds((long) 4, "Basic", 2, 5, 2, 2, 2, 3, fourthMatch, null);
    	Odds fifthOdd = new Odds((long) 5, "Basic", 2, 2, 4, 2, 3, 3, fifthMatch, null);
    	Odds sixthOdd = new Odds((long) 6, "Basic", 2, 2, 2, 2, 3, 3, sixthMatch, null);

    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));
    	ticketOdds.add(new TicketOdds(3, ticket, thirdOdd, (long) 2, "2"));
    	ticketOdds.add(new TicketOdds(4, ticket, fourthOdd, (long) 2, "12"));
    	ticketOdds.add(new TicketOdds(5, ticket, fifthOdd, (long) 2, "X2"));
    	ticketOdds.add(new TicketOdds(6, ticket, sixthOdd, (long) 2, "X2"));
    	
        mvc.perform(MockMvcRequestBuilders.post("/api/ticket")
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().string("Successfully placed a bet!"));
    }
    
    @Test
    public void givenArrayOfTicketOdds_whenPlayingTicketWithNotEnoughMoney_thenBadRequest()
      throws Exception {
    	
        Instant matchDate = Instant.now().plusSeconds(60);
    	User ljubo = new User(1, "Ljubo Mamic", "Split", 24, 500, null);

        given(userService.findUserById((long) 1)).willReturn(Optional.of(ljubo));

        Transaction newTransaction = new Transaction((long) 1, null, true,  600, ljubo, null);
    	Ticket ticket = new Ticket((long) 1, 4, 800, newTransaction, null);
    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
    	Odds secondOdd = new Odds((long) 2, "Basic", 2, 2, 2, 2, 3, 3, secondMatch, null);

    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));
    	
        mvc.perform(MockMvcRequestBuilders.post("/api/ticket")
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You don't have enough money in your wallet."));
    }
    
    @Test
    public void givenArrayOfTicketOdds_whenPlayingTicketWithNotEnoughBetMoney_thenBadRequest()
      throws Exception {
    	
        Instant matchDate = Instant.now().plusSeconds(60);
    	User ljubo = new User(1, "Ljubo Mamic", "Split", 24, 500, null);

        given(userService.findUserById((long) 1)).willReturn(Optional.of(ljubo));

        Transaction newTransaction = new Transaction((long) 1, null, true,  0, ljubo, null);
    	Ticket ticket = new Ticket((long) 1, 4, 800, newTransaction, null);
    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
    	Odds secondOdd = new Odds((long) 2, "Basic", 2, 2, 2, 2, 3, 3, secondMatch, null);

    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));
    	
        mvc.perform(MockMvcRequestBuilders.post("/api/ticket")
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You have to bet at least 1 HRK"));
    }
    
    @Test
    public void givenArrayOfTicketOddsWithSpecialOffer_whenPlayingTicketWithNotEnoughBiggerOdds_thenBadRequest()
      throws Exception {
    	
        int moneyInWalletBefore = 200;
        int spentMoney = 200;
        Instant matchDate = Instant.now().plusSeconds(60);
        
    	User ljuboBeforeBet = new User(1, "Ljubo Mamic", "Split", 24, moneyInWalletBefore, null);
    	User ljuboAfterBet = new User(1, "Ljubo Mamic", "Split", 24, moneyInWalletBefore - spentMoney, null);
        Transaction newTransaction = new Transaction((long) 1, null, true,  spentMoney, ljuboAfterBet, null);
        
        given(userService.findUserById((long) 1)).willReturn(Optional.of(ljuboBeforeBet));
    	given(userService.changeMoneyValueInWallet((long) 1, 200, false)).willReturn(Optional.of(ljuboAfterBet));
    	given(transactionService.createTransaction(newTransaction, true)).willReturn(newTransaction);

    	Ticket ticket = new Ticket((long) 1, 4, 800, newTransaction, null);
    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
    	Odds secondOdd = new Odds((long) 1, "Special Offer", 2, 2, 2, 2, 3, 3, secondMatch, null);

    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));
    	
        mvc.perform(MockMvcRequestBuilders.post("/api/ticket")
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You didn't place a valid bet."));
    }
    
    @Test
    public void givenArrayOfTicketOddsWithSpecialOffer_whenPlayingTicketWithMoreSpecialOffers_thenBadRequest()
      throws Exception {
    	
        int moneyInWalletBefore = 200;
        int spentMoney = 200;
        Instant matchDate = Instant.now().plusSeconds(60);
        
    	User ljuboBeforeBet = new User(1, "Ljubo Mamic", "Split", 24, moneyInWalletBefore, null);
    	User ljuboAfterBet = new User(1, "Ljubo Mamic", "Split", 24, moneyInWalletBefore - spentMoney, null);
        Transaction newTransaction = new Transaction((long) 1, null, true,  spentMoney, ljuboAfterBet, null);
        
        given(userService.findUserById((long) 1)).willReturn(Optional.of(ljuboBeforeBet));
    	given(userService.changeMoneyValueInWallet((long) 1, 200, false)).willReturn(Optional.of(ljuboAfterBet));
    	given(transactionService.createTransaction(newTransaction, true)).willReturn(newTransaction);

    	Ticket ticket = new Ticket((long) 1, 4, 800, newTransaction, null);
    	
    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", null, null);
    	Match thirdMatch = new Match((long) 3, matchDate, "Rogger Federer", "Rafael Nadal", null, null);
    	Match fourthMatch = new Match((long) 4, matchDate, "Hajduk", "Dinamo", null, null);
    	Match fifthMatch = new Match((long) 5, matchDate, "Rijeka", "Osijek", null, null);
    	Match sixthMatch = new Match((long) 6, matchDate, "Solin", "Kastela", null, null);
    	
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
    	Odds secondOdd = new Odds((long) 2, "Special Offer", 2, 2, 2, 2, 3, 3, secondMatch, null);
    	Odds thirdOdd = new Odds((long) 3, "Special offer", 2, 2, 2, 2, 3, 3, thirdMatch, null);
    	Odds fourthOdd = new Odds((long) 4, "Basic", 2, 5, 2, 2, 2, 3, fourthMatch, null);
    	Odds fifthOdd = new Odds((long) 5, "Basic", 2, 2, 4, 2, 3, 3, fifthMatch, null);
    	Odds sixthOdd = new Odds((long) 6, "Basic", 2, 2, 2, 2, 3, 3, sixthMatch, null);

    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));
    	ticketOdds.add(new TicketOdds(3, ticket, thirdOdd, (long) 2, "2"));
    	ticketOdds.add(new TicketOdds(4, ticket, fourthOdd, (long) 2, "12"));
    	ticketOdds.add(new TicketOdds(5, ticket, fifthOdd, (long) 2, "X2"));
    	ticketOdds.add(new TicketOdds(6, ticket, sixthOdd, (long) 2, "X2"));
    	
        mvc.perform(MockMvcRequestBuilders.post("/api/ticket")
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You didn't place a valid bet."));
        }
    
    @Test
    public void givenArrayOfTicketOddsWithSpecialOffer_whenPlayingTicketWithSameMatchAsSpecialOfferAndBasicOffer_thenBadRequest()
      throws Exception {
    	
        int moneyInWalletBefore = 200;
        int spentMoney = 200;
        Instant matchDate = Instant.now().plusSeconds(60);
        
    	User ljuboBeforeBet = new User(1, "Ljubo Mamic", "Split", 24, moneyInWalletBefore, null);
    	User ljuboAfterBet = new User(1, "Ljubo Mamic", "Split", 24, moneyInWalletBefore - spentMoney, null);
        Transaction newTransaction = new Transaction((long) 1, null, true,  spentMoney, ljuboAfterBet, null);
        
        given(userService.findUserById((long) 1)).willReturn(Optional.of(ljuboBeforeBet));
    	given(userService.changeMoneyValueInWallet((long) 1, 200, false)).willReturn(Optional.of(ljuboAfterBet));
    	given(transactionService.createTransaction(newTransaction, true)).willReturn(newTransaction);

    	Ticket ticket = new Ticket((long) 1, 4, 800, newTransaction, null);
    	
    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", null, null);
    	Match thirdMatch = new Match((long) 3, matchDate, "Rogger Federer", "Rafael Nadal", null, null);
    	Match fourthMatch = new Match((long) 4, matchDate, "Hajduk", "Dinamo", null, null);
    	Match fifthMatch = new Match((long) 5, matchDate, "Rijeka", "Osijek", null, null);
    	
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
    	Odds secondOdd = new Odds((long) 2, "Special Offer", 2, 2, 2, 2, 3, 3, secondMatch, null);
    	Odds thirdOdd = new Odds((long) 3, "Basic", 2, 2, 2, 2, 3, 3, secondMatch, null);
    	Odds fourthOdd = new Odds((long) 4, "Basic", 2, 5, 2, 2, 2, 3, thirdMatch, null);
    	Odds fifthOdd = new Odds((long) 5, "Basic", 2, 2, 4, 2, 3, 3, fourthMatch, null);
    	Odds sixthOdd = new Odds((long) 6, "Basic", 2, 2, 2, 2, 3, 3, fifthMatch, null);

    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));
    	ticketOdds.add(new TicketOdds(3, ticket, thirdOdd, (long) 2, "2"));
    	ticketOdds.add(new TicketOdds(4, ticket, fourthOdd, (long) 2, "12"));
    	ticketOdds.add(new TicketOdds(5, ticket, fifthOdd, (long) 2, "X2"));
    	ticketOdds.add(new TicketOdds(6, ticket, sixthOdd, (long) 2, "X2"));
    	
        mvc.perform(MockMvcRequestBuilders.post("/api/ticket")
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You didn't place a valid bet."));
        }
    
    @Test
    public void givenArrayOfTicketOdds_whenPlayingTicketWithOutdateMatch_thenBadRequest()
      throws Exception {
    	
        Instant matchDate = Instant.now().minusSeconds(60);
    	User ljubo = new User(1, "Ljubo Mamic", "Split", 24, 500, null);

        given(userService.findUserById((long) 1)).willReturn(Optional.of(ljubo));

        Transaction newTransaction = new Transaction((long) 1, null, true, 200, ljubo, null);
    	Ticket ticket = new Ticket((long) 1, 4, 800, newTransaction, null);
    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
    	Odds secondOdd = new Odds((long) 2, "Basic", 2, 2, 2, 2, 3, 3, secondMatch, null);

    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));
    	
        mvc.perform(MockMvcRequestBuilders.post("/api/ticket")
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You didn't place a valid bet."));
    }
    
    public static String asJsonString(final Collection<TicketOdds> ticketOdds) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            JavaTimeModule module = new JavaTimeModule();
            mapper.registerModule(module);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            final String jsonContent = mapper.writeValueAsString(ticketOdds);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
      }
}
