package com.example.Betting;

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

import com.example.Betting.controller.TicketController;
import com.example.Betting.model.Ticket;
import com.example.Betting.service.TicketService;

@RunWith(SpringRunner.class)
@WebMvcTest(TicketController.class)
public class TicketControllerTests {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private TicketService ticketService;
    
    @Test
    public void whenGetTickets_thenArrayOfTickets()
      throws Exception {
        
    	Collection<Ticket> ticket = new ArrayList<Ticket>();
    	ticket.add(new Ticket((long) 2, 4, 400, null, null));
    	ticket.add(new Ticket((long) 1, 2, 200, null, null));
    	given(ticketService.findAllTickets()).willReturn(ticket);

        mvc.perform(MockMvcRequestBuilders.get("/api/tickets")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
          		"    {\r\n" + 
          		"        \"id\": 2,\r\n" + 
          		"        \"totalodd\": 4,\r\n" + 
          		"        \"possiblegain\": 400,\r\n" + 
          		"        \"transaction\": null\r\n" + 
          		"    },\r\n" + 
          		"    {\r\n" + 
          		"        \"id\": 1,\r\n" + 
          		"        \"totalodd\": 2,\r\n" + 
          		"        \"possiblegain\": 200,\r\n" + 
          		"        \"transaction\": null\r\n" + 
          		"    }\r\n" + 
          		"]"));
    }
    
    @Test
    public void whenGetTickets_thenTickets()
      throws Exception {

    	Collection<Ticket> ticket = new ArrayList<Ticket>();
    	ticket.add(new Ticket((long) 1, 4, 400, null, null));

    	given(ticketService.findAllTickets()).willReturn(ticket);

        mvc.perform(MockMvcRequestBuilders.get("/api/tickets")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
          		"    {\r\n" + 
          		"        \"id\": 1,\r\n" + 
          		"        \"totalodd\": 4,\r\n" + 
          		"        \"possiblegain\": 400,\r\n" + 
          		"        \"transaction\": null\r\n" + 
          		"    }\r\n" + 
          		"]"));
    }
    
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
