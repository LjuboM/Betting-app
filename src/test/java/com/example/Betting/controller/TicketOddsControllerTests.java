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

import com.example.Betting.model.Match;
import com.example.Betting.model.Odds;
import com.example.Betting.model.Ticket;
import com.example.Betting.model.TicketOdds;
import com.example.Betting.model.User;
import com.example.Betting.service.TicketOddsService;
import com.example.Betting.service.TransactionService;
import com.example.Betting.service.UserService;
import com.example.Betting.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * The Class TicketOddsControllerTests.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TicketOddsController.class)
public class TicketOddsControllerTests {

    /** The mvc. */
    @Autowired
    private MockMvc mvc;

    /** The ticket odds service. */
    @MockBean
    private TicketOddsService ticketOddsService;

    /** The user service. */
    @MockBean
    private UserService userService;

    /** The transaction service. */
    @MockBean
    private TransactionService transactionService;

    /**
     * When get ticket odds then array of ticket odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetTicketOdds_thenArrayOfTicketOdds()
      throws Exception {

    	Ticket ticket = new Ticket((long) 1, 4, 360, Constants.STATUS_WAITING, null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, null, null);
    	Odds secondOdd = new Odds((long) 1, "Basic", 2, 2, 2, 2, 3, 3, null, null);

    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));
    	given(ticketOddsService.findAllPlayedTicketPairs()).willReturn(ticketOdds);

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
                "            \"possiblegain\": 360,\r\n" +  
          		"            \"status\": 0\r\n" + 
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
                "            \"possiblegain\": 360,\r\n" + 
          		"            \"status\": 0\r\n" + 
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

    /**
     * When get ticket odds then ticket odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetTicketOdds_thenTicketOdds()
      throws Exception {

    	Ticket ticket = new Ticket((long) 1, 4, 360, Constants.STATUS_WAITING, null, null);
    	Odds odd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, null, null);
    	Collection<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, odd, (long) 2, "1"));

    	given(ticketOddsService.findAllPlayedTicketPairs()).willReturn(ticketOdds);

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
          		"            \"possiblegain\": 360,\r\n" + 
          		"            \"status\": 0\r\n" + 
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

    /**
     * When get ticket odds then no ticket odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetTicketOdds_thenNoTicketOdds()
      throws Exception {

    	given(ticketOddsService.findAllPlayedTicketPairs()).willReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/api/ticketOdds")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    /**
     * When get match pairs by ticket id then array of ticket odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetMatchPairsByTicketId_thenArrayOfTicketOdds()
        throws Exception {

    	long ticketId = 1;

    	Ticket ticket = new Ticket((long) 1, 4, 360, Constants.STATUS_WAITING, null, null);
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
              		"            \"possiblegain\": 360,\r\n" + 
              		"            \"status\": 0\r\n" + 
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
                    "            \"possiblegain\": 360,\r\n" + 
              		"            \"status\": 0\r\n" + 
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

    /**
     * When get match pairs by ticket id then ticket odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetMatchPairsByTicketId_thenTicketOdds()
        throws Exception {

    	long ticketId = 1;
    	Ticket ticket = new Ticket((long) 1, 4, 360, Constants.STATUS_WAITING, null, null);
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
              		"            \"possiblegain\": 360,\r\n" + 
              		"            \"status\": 0\r\n" + 
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

    /**
     * When get match pairs by ticket id then no ticket odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetMatchPairsByTicketId_thenNoTicketOdds()
        throws Exception {

    	long ticketId = 1;
        given(ticketOddsService.findAllPlayedPairsByTicketId(ticketId)).willReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/api/ticketOdds/{ticketId}", ticketId)
           .contentType(MediaType.APPLICATION_JSON))
           .andExpect(MockMvcResultMatchers.status().isNotFound());
        }
 
    /**
     * Given array of basic ticket odds when playing ticket then ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenArrayOfBasicTicketOdds_whenPlayingTicket_thenOk()
      throws Exception {
        float moneyInWalletBefore = 200;
        float spentMoney = 200;
        Instant matchDate = Instant.now().plusSeconds(60);

        Ticket ticket = new Ticket((long) 1, 4, 684, Constants.STATUS_WAITING, null, null);
        
    	User userBeforeBet = new User(1, "John Doe", "Split", 24, moneyInWalletBefore, null);
    	User userAfterBet = new User(1, "John Doe", "Split", 24, moneyInWalletBefore - spentMoney, null);
    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
    	Odds secondOdd = new Odds((long) 2, "Basic", 2, 2, 2, 2, 3, 3, secondMatch, null);

    	ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));

        given(userService.findUserById((long) 1)).willReturn(Optional.of(userBeforeBet));
        given(userService.changeMoneyValueInWallet(userBeforeBet, spentMoney, false)).willReturn(userAfterBet);
        given(transactionService.createTransaction(spentMoney, ticket, userAfterBet, 1)).willReturn(true);
        given(ticketOddsService.validateNewTicket(ticketOdds)).willReturn(4f);
        given(userService.checkEnoughMoneyInWallet(spentMoney, userBeforeBet)).willReturn(true);

        mvc.perform(MockMvcRequestBuilders.post("/api/ticket/{spentMoney}", spentMoney)
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().string("Successfully placed a bet!"));
    }

    /**
     * Given pair of basic ticket odds when playing ticket then ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenPairOfBasicTicketOdds_whenPlayingTicket_thenOk()
      throws Exception {

        float moneyInWalletBefore = 400;
        float spentMoney = 200;

        Instant matchDate = Instant.now().plusSeconds(60);

        Ticket ticket = new Ticket((long) 1, 4, 684, Constants.STATUS_WAITING, null, null);
        
    	User userBeforeBet = new User(1, "John Doe", "Split", 24, moneyInWalletBefore, null);
    	User userAfterBet = new User(1, "John Doe", "Split", 24, moneyInWalletBefore - spentMoney, null);

    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);

    	ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();

    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
        given(userService.findUserById((long) 1)).willReturn(Optional.of(userBeforeBet));
        given(userService.changeMoneyValueInWallet(userBeforeBet, spentMoney, false)).willReturn(userAfterBet);
        given(transactionService.createTransaction(spentMoney, ticket, userAfterBet, 1)).willReturn(true);
        given(ticketOddsService.validateNewTicket(ticketOdds)).willReturn(4f);
        given(userService.checkEnoughMoneyInWallet(spentMoney, userBeforeBet)).willReturn(true);

        mvc.perform(MockMvcRequestBuilders.post("/api/ticket/{spentMoney}", spentMoney)
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().string("Successfully placed a bet!"));
    }

    /**
     * Given pair of ticket odds with with wrong odds when playing ticket then not ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenPairOfTicketOddsWithWrongOdds_whenPlayingTicket_thenNotOk()
      throws Exception {

        float spentMoney = 10;
    	ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, null, null, (long) 2, "1"));

        given(ticketOddsService.validateNewTicket(ticketOdds)).willReturn(0f);

        mvc.perform(MockMvcRequestBuilders.post("/api/ticket/{spentMoney}", spentMoney)
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You didn't place a valid bet."));
    }

    /**
     * Given array of ticket odds when playing ticket with not enough money then bad request.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenArrayOfTicketOdds_whenPlayingTicketWithNotEnoughMoney_thenBadRequest()
      throws Exception {
        float spentMoney = 631.578947f;
        Instant matchDate = Instant.now().plusSeconds(60);
    	User initialUser = new User(1, "John Doe", "Split", 24, 500, null);
        Ticket ticket = new Ticket((long) 1, 2, 900, Constants.STATUS_WAITING, null, null);   
    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
    	Odds secondOdd = new Odds((long) 2, "Basic", 2, 2, 2, 2, 3, 3, secondMatch, null);

    	ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));

        given(userService.findUserById((long) 1)).willReturn(Optional.of(initialUser));
        given(ticketOddsService.validateNewTicket(ticketOdds)).willReturn(4f);

        mvc.perform(MockMvcRequestBuilders.post("/api/ticket/{spentMoney}", spentMoney)
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You don't have enough money in your wallet."));
    }

    /**
     * Given array of ticket odds when playing ticket with wrong user then bad request.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenArrayOfTicketOdds_whenPlayingTicketWithWrongUser_thenBadRequest()
      throws Exception {
        float spentMoney = 10f;

        ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
        ticketOdds.add(new TicketOdds(1, null, null, (long) 2, "1"));

        given(ticketOddsService.validateNewTicket(ticketOdds)).willReturn(4f);

        mvc.perform(MockMvcRequestBuilders.post("/api/ticket/{spentMoney}", spentMoney)
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("Invalid user!"));
    }

    /**
     * Given array of ticket odds when playing ticket with not enough bet money then bad request.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenArrayOfTicketOdds_whenPlayingTicketWithNotEnoughBetMoney_thenBadRequest()
      throws Exception {
        float spentMoney = 0.999f;
        Instant matchDate = Instant.now().plusSeconds(60);
    	User initialUser = new User(1, "John Doe", "Split", 24, 500, null);


        //0.9499f mimics betting just bellow 1 HRK
        Ticket ticket = new Ticket((long) 1, 2f, 1.423575f, Constants.STATUS_WAITING, null, null);
        
    	Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
    	Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", null, null);
    	Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
    	Odds secondOdd = new Odds((long) 2, "Basic", 2, 2, 2, 2, 3, 3, secondMatch, null);

    	ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
    	ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
    	ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));

        given(userService.findUserById((long) 1)).willReturn(Optional.of(initialUser));
        given(ticketOddsService.validateNewTicket(ticketOdds)).willReturn(4f);
        given(userService.checkEnoughMoneyInWallet(spentMoney, initialUser)).willReturn(true);

        mvc.perform(MockMvcRequestBuilders.post("/api/ticket/{spentMoney}", spentMoney)
          .content(asJsonString(ticketOdds))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.content().string("You have to bet at least 1 HRK"));
    }

    /**
     * As json string.
     *
     * @param ticketOdds the ticket odds
     * @return the string
     */
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
