package com.example.Betting.controller;

import static org.mockito.BDDMockito.given;

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

import com.example.Betting.model.Ticket;
import com.example.Betting.service.TicketService;

/**
 * The Class TicketControllerTests.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TicketController.class)
public class TicketControllerTests {

    /** The mvc. */
    @Autowired
    private MockMvc mvc;

    /** The ticket service. */
    @MockBean
    private TicketService ticketService;

    /**
     * When get tickets then array of tickets.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetTickets_thenArrayOfTickets()
      throws Exception {
        
    	Collection<Ticket> ticket = new ArrayList<Ticket>();
    	ticket.add(new Ticket((long) 2, 4, 400, 0, null, null));
    	ticket.add(new Ticket((long) 1, 2, 200, 0, null, null));
    	given(ticketService.findAllTickets()).willReturn(ticket);

        mvc.perform(MockMvcRequestBuilders.get("/api/tickets")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
                  "    {\r\n" + 
                  "        \"id\": 2,\r\n" + 
                  "        \"totalodd\": 4.0,\r\n" + 
                  "        \"possiblegain\": 400.0,\r\n" + 
                  "        \"status\": 0\r\n" + 
                  "    },\r\n" + 
                  "    {\r\n" + 
                  "        \"id\": 1,\r\n" + 
                  "        \"totalodd\": 2.0,\r\n" + 
                  "        \"possiblegain\": 200.0,\r\n" + 
                  "        \"status\": 0\r\n" + 
                  "    }\r\n" + 
                  "]"));
    }

    /**
     * When get tickets then tickets.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetTickets_thenTickets()
      throws Exception {

    	Collection<Ticket> ticket = new ArrayList<Ticket>();
    	ticket.add(new Ticket((long) 1, 4, 400, 0, null, null));

    	given(ticketService.findAllTickets()).willReturn(ticket);

        mvc.perform(MockMvcRequestBuilders.get("/api/tickets")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
                  "    {\r\n" + 
                  "        \"id\": 1,\r\n" + 
                  "        \"totalodd\": 4.0,\r\n" + 
                  "        \"possiblegain\": 400.0,\r\n" + 
                  "        \"status\": 0\r\n" + 
                  "    }\r\n" + 
                  "]"));
    }

    /**
     * When get tickets then no ticket odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetTickets_thenNoTicketOdds()
      throws Exception {

    	given(ticketService.findAllTickets()).willReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/api/tickets")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

}
