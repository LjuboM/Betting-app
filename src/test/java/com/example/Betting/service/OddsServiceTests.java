package com.example.Betting.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Betting.model.Match;
import com.example.Betting.model.Odds;
import com.example.Betting.repository.OddsRepository;


/**
 * The Class OddsServiceTest.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(OddsService.class)
public class OddsServiceTests {

    /** The odds repository. */
    @MockBean
    private OddsRepository oddsRepository;

    /** The odds service. */
    @Autowired
    private OddsService oddsService;

    /**
     * When find all sorted odds then return all odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenfindAllSortedOdds_thenAllOdds()
      throws Exception {

        Match match1 = new Match(0, Instant.parse("3020-03-03T17:00:00Z"), "FC Barcelona", "Real Madrid C.F.", null, null);
        Match match2 = new Match(1, Instant.parse("3020-05-15T17:00:00Z"), "Cibona", "Cedevita", null, null);

        Collection<Odds> odds = new ArrayList<Odds>();
        odds.add(new Odds(0, "Basic",(float) 2.0, (float) 2.0, (float) 2.0, (float) 2.0, (float) 1.0, (float) 5.0, match1, null));
        odds.add(new Odds(1, "Special offer", (float) 2.1, (float) 2.1, (float) 2.1, (float) 2.1, (float) 1.1, (float) 5.2, match2, null));

        Collection<Odds> expectedOdds = new ArrayList<Odds>();
        expectedOdds.add(new Odds(0, "Basic",(float) 2.0, (float) 2.0, (float) 2.0, (float) 2.0, (float) 1.0, (float) 5.0, match1, null));
        expectedOdds.add(new Odds(1, "Special offer", (float) 2.1, (float) 2.1, (float) 2.1, (float) 2.1, (float) 1.1, (float) 5.2, match2, null));
 
        given(oddsRepository.findAll(Sort.by(Sort.Direction.ASC, "match.matchdate"))).willReturn((List<Odds>) odds);

        Collection<Odds> returnedOdds = oddsService.findAllSortedOdds();

        assertEquals(expectedOdds, returnedOdds);
    }
    
    /**
     * When find all sorted odds then don't return odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenfindAllSortedOdds_thenNoOdds()
      throws Exception {

        Match match1 = new Match(0, Instant.parse("2020-01-01T17:00:00Z"), "FC Barcelona", "Real Madrid C.F.", null, null);
        Match match2 = new Match(1, Instant.parse("2020-02-12T17:00:00Z"), "Cibona", "Cedevita", null, null);

        Collection<Odds> odds = new ArrayList<Odds>();
        odds.add(new Odds(0, "Basic",(float) 2.0, (float) 2.0, (float) 2.0, (float) 2.0, (float) 1.0, (float) 5.0, match1, null));
        odds.add(new Odds(1, "Special offer", (float) 2.1, (float) 2.1, (float) 2.1, (float) 2.1, (float) 1.1, (float) 5.2, match2, null));

        Collection<Odds> expectedOdds = new ArrayList<Odds>();
 
        given(oddsRepository.findAll(Sort.by(Sort.Direction.ASC, "match.matchdate"))).willReturn((List<Odds>) odds);

        Collection<Odds> returnedOdds = oddsService.findAllSortedOdds();

        assertEquals(expectedOdds, returnedOdds);
    }
    
    /**
     * When find all sorted odds then return future odds.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenfindAllSortedOdds_thenFutureOdds()
      throws Exception {

        Match match1 = new Match(0, Instant.parse("5020-05-05T17:00:00Z"), "FC Barcelona", "Real Madrid C.F.", null, null);
        Match match2 = new Match(1, Instant.parse("2020-01-01T17:00:00Z"), "Cibona", "Cedevita", null, null);

        Collection<Odds> odds = new ArrayList<Odds>();
        odds.add(new Odds(0, "Basic",(float) 2.0, (float) 2.0, (float) 2.0, (float) 2.0, (float) 1.0, (float) 5.0, match1, null));
        odds.add(new Odds(1, "Special offer", (float) 2.1, (float) 2.1, (float) 2.1, (float) 2.1, (float) 1.1, (float) 5.2, match2, null));

        Collection<Odds> expectedOdds = new ArrayList<Odds>();
        expectedOdds.add(new Odds(0, "Basic",(float) 2.0, (float) 2.0, (float) 2.0, (float) 2.0, (float) 1.0, (float) 5.0, match1, null));
 
        given(oddsRepository.findAll(Sort.by(Sort.Direction.ASC, "match.matchdate"))).willReturn((List<Odds>) odds);

        Collection<Odds> returnedOdds = oddsService.findAllSortedOdds();

        assertEquals(expectedOdds, returnedOdds);
    }

}
