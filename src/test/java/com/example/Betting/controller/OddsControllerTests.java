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

import com.example.Betting.controller.OddsController;
import com.example.Betting.model.Odds;
import com.example.Betting.service.OddsService;

/**
 * The Class OddsControllerTests.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(OddsController.class)
public class OddsControllerTests {

    /** The mvc. */
    @Autowired
    private MockMvc mvc;

    /** The odds service. */
    @MockBean
    private OddsService oddsService;

    /**
     * When get odds then array of odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetOdds_thenArrayOfOdds()
      throws Exception {

    	Collection<Odds> odds = new ArrayList<Odds>();
    	odds.add(new Odds( 0, "Basic",(float) 2.0, (float) 2.0, (float) 2.0, (float) 2.0, (float) 1.0, (float) 5.0, null, null));
    	odds.add(new Odds( 1, "Special offer", (float) 2.1, (float) 2.1, (float) 2.1, (float) 2.1, (float) 1.1, (float) 5.2, null, null));
    	given(oddsService.findAllSortedOdds()).willReturn(odds);

        mvc.perform(MockMvcRequestBuilders.get("/api/odds")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
          		"    {\r\n" + 
          		"        \"id\": 0,\r\n" + 
          		"        \"type\": \"Basic\",\r\n" + 
          		"        \"odd1\": 2,\r\n" + 
          		"        \"odd2\": 2,\r\n" + 
          		"        \"odd3\": 2,\r\n" + 
          		"        \"odd4\": 2,\r\n" + 
          		"        \"odd5\": 1,\r\n" + 
          		"        \"odd6\": 5,\r\n" + 
          		"        \"match\": null\r\n" + 
          		"    },\r\n" + 
          		"    {\r\n" + 
          		"        \"id\": 1,\r\n" + 
          		"        \"type\": \"Special offer\",\r\n" + 
          		"        \"odd1\": 2.1,\r\n" + 
          		"        \"odd2\": 2.1,\r\n" + 
          		"        \"odd3\": 2.1,\r\n" + 
          		"        \"odd4\": 2.1,\r\n" + 
          		"        \"odd5\": 1.1,\r\n" + 
          		"        \"odd6\": 5.2,\r\n" + 
          		"        \"match\": null\r\n" + 
          		"    }\r\n" + 
          		"]"));
    }

    /**
     * When get odds then odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetOdds_thenOdds()
      throws Exception {

    	Collection<Odds> odds = new ArrayList<Odds>();
    	odds.add(new Odds( 0, "Basic",(float) 2.0, (float) 2.0, (float) 2.0, (float) 2.0, (float) 1.0, (float) 5.0, null, null));
    	given(oddsService.findAllSortedOdds()).willReturn(odds);

        mvc.perform(MockMvcRequestBuilders.get("/api/odds")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
          		"    {\r\n" + 
          		"        \"id\": 0,\r\n" + 
          		"        \"type\": \"Basic\",\r\n" + 
          		"        \"odd1\": 2,\r\n" + 
          		"        \"odd2\": 2,\r\n" + 
          		"        \"odd3\": 2,\r\n" + 
          		"        \"odd4\": 2,\r\n" + 
          		"        \"odd5\": 1,\r\n" + 
          		"        \"odd6\": 5,\r\n" + 
          		"        \"match\": null\r\n" + 
          		"    }\r\n" + 
          		"]"));
    }

    /**
     * When get odds then no odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetOdds_thenNoOdds()
      throws Exception {

    	given(oddsService.findAllSortedOdds()).willReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/api/odds")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

}
