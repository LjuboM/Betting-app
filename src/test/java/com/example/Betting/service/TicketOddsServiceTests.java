package com.example.Betting.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Betting.model.Match;
import com.example.Betting.model.Odds;
import com.example.Betting.model.Ticket;
import com.example.Betting.model.TicketOdds;
import com.example.Betting.model.Types;
import com.example.Betting.repository.OddsRepository;
import com.example.Betting.repository.TicketOddsRepository;


/**
 * The Class TicketOddsServiceTest.
 */
@RunWith(SpringRunner.class)
@WebMvcTest({TicketOddsService.class, OddsService.class})
public class TicketOddsServiceTests {

    /** The ticketOdds repository. */
    @MockBean
    private TicketOddsRepository ticketOddsRepository;
    
    /** The ticketOdds repository. */
    @MockBean
    private OddsRepository oddsRepository;

    /** The ticketOdds service. */
    @Autowired
    private TicketOddsService ticketOddsService;
    
    /** The Odds service. */
    @Autowired
    private OddsService oddsService;

    private boolean result;
    
    /**
     * Given total odd and spent money when creating ticket then ticket.
     *
     * @throws Exception the exception
     */
    @Test
    public void giventotalOddAndSpentMoney_whencreateTicket_thenTicket()
      throws Exception {
        final float totalOdd = 10;
        final float spentMoney = 10;
        final Ticket ticket = new Ticket((long) 0, 10, 85.5f, 0, null, null);
        Ticket returnedTicket = ticketOddsService.createTicket(totalOdd, spentMoney);

        assertEquals(ticket, returnedTicket);
    }

    /**
     * Given less than 10 000 possible gain when calculating taxes then taxes.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenLessThan10000_whencalculateTicketTaxes_thenTaxes()
      throws Exception {
        result = false;
        final float possibleGain = 9000;
        //Allowed difference when comparing two float values, in this case, odd values.
        final float epsilon = 0.0001f;
        float taxes = ticketOddsService.calculateTicketTaxes(possibleGain);

        if (Math.abs(900f - taxes) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given less than 30 000 possible gain when calculating taxes then taxes.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenLessThan30000_whencalculateTicketTaxes_thenTaxes()
      throws Exception {
        result = false;
        final float possibleGain = 29000;
        //Allowed difference when comparing two float values, in this case, odd values.
        final float epsilon = 0.0001f;
        float taxes = ticketOddsService.calculateTicketTaxes(possibleGain);

        if (Math.abs(3850f - taxes) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given less than 500 000 possible gain when calculating taxes then taxes.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenLessThan500000_whencalculateTicketTaxes_thenTaxes()
      throws Exception {
        result = false;
        final float possibleGain = 49000;
        //Allowed difference when comparing two float values, in this case, odd values.
        final float epsilon = 0.0001f;
        float taxes = ticketOddsService.calculateTicketTaxes(possibleGain);

        if (Math.abs(7800f - taxes) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given more than 500 000 possible gain when calculating taxes then taxes.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenMoreThan500000_whencalculateTicketTaxes_thenTaxes()
      throws Exception {
        result = false;
        final float possibleGain = 510000;
        //Allowed difference when comparing two float values, in this case, odd values.
        final float epsilon = 0.0001f;
        float taxes = ticketOddsService.calculateTicketTaxes(possibleGain);

        if (Math.abs(101000f - taxes) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given 500 000 possible gain when calculating taxes then taxes.
     *
     * @throws Exception the exception
     */
    @Test
    public void given500000_whencalculateTicketTaxes_thenTaxes()
      throws Exception {
        result = false;
        final float possibleGain = 500000;
        //Allowed difference when comparing two float values, in this case, odd values.
        final float epsilon = 0.0001f;
        float taxes = ticketOddsService.calculateTicketTaxes(possibleGain);

        if (Math.abs(98000f - taxes) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given array of basic ticket odds when validating new ticket then ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenArrayOfBasicTicketOdds_whenValidateNewTicket_thenOk()
      throws Exception {
        result = false;
        Instant matchDate = Instant.now().plusSeconds(60);

        Ticket ticket = new Ticket((long) 1, 4, 684, 0, null, null);

        Types firstTypes = new Types((long) 1, "Football", "1", "X", "2", "1X", "X2", "12", null);
        Types secondTypes = new Types((long) 2, "Basketball", "1X", "X2", ">=200p", "<200p", "1H10", "2H10", null);

        Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", firstTypes, null);
        Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", secondTypes, null);
        Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
        Odds secondOdd = new Odds((long) 2, "Basic", 2, 2, 2, 2, 3, 3, secondMatch, null);

        ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
        ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
        ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X2"));

        given(oddsService.findOddsById((long) 1)).willReturn(Optional.of(firstOdd));
        given(oddsService.findOddsById((long) 2)).willReturn(Optional.of(secondOdd));
        
        float totalOdd = ticketOddsService.validateNewTicket(ticketOdds);
        
        //Allowed difference when comparing two float values, in this case, total odd values.
        final float epsilon = 0.0001f;
        if (Math.abs(4f - totalOdd) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given one pair of basic ticket odds when validating new ticket then ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenOnePairOfBasicTicketOdds_whenValidateNewTicket_thenOk()
      throws Exception {
        result = false;
        Instant matchDate = Instant.now().plusSeconds(60);

        Ticket ticket = new Ticket((long) 1, 4, 684, 0, null, null);

        Types firstTypes = new Types((long) 1, "Football", "1", "X", "2", "1X", "X2", "12", null);

        Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", firstTypes, null);
        Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);

        ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
        ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));

        given(oddsService.findOddsById((long) 1)).willReturn(Optional.of(firstOdd));
 
        float totalOdd = ticketOddsService.validateNewTicket(ticketOdds);

        //Allowed difference when comparing two float values, in this case, total odd values.
        final float epsilon = 0.0001f;
        if (Math.abs(2f - totalOdd) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given array of ticket odds with special offer when validating new ticket with enough bigger odds then ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenArrayOfTicketOddsWithSpecialOffer_whenValidatingNewTicketWithEnoughBiggerOdds_thenOk()
      throws Exception {
        result = false;

        Instant matchDate = Instant.now().plusSeconds(60);

        Ticket ticket = new Ticket((long) 1, 4, 684, 0, null, null);

        Types firstTypes = new Types((long) 1, "Football", "1", "X", "2", "1X", "X2", "12", null);
        Types secondTypes = new Types((long) 2, "Basketball", "1X", "X2", ">=200p", "<200p", "1H10", "2H10", null);
        Types thirdTypes = new Types((long) 3, "Tennis", "1", "2", ">23.5g", "<23.5g", ">=2.5h", "<2.5h", null);
        Types fourthTypes = new Types((long) 4, "Waterpolo", "1", "X", "2", ">15e", "<15e", "Canceled", null);
        Types fifthTypes = new Types((long) 5, "Boxing", "1", "2", "Nockout", "Full Match", "Blood", "No Blood", null);

        Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", firstTypes, null);
        Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", fourthTypes, null);
        Match thirdMatch = new Match((long) 3, matchDate, "Rogger Federer", "Rafael Nadal", thirdTypes, null);
        Match fourthMatch = new Match((long) 4, matchDate, "Hajduk", "Dinamo", firstTypes, null);
        Match fifthMatch = new Match((long) 5, matchDate, "Cibona", "Cedevita", secondTypes, null);
        Match sixthMatch = new Match((long) 6, matchDate, "Mike Tyson", "Muhammad Ali", fifthTypes, null);

        Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
        Odds secondOdd = new Odds((long) 2, "Special Offer", 2, 2, 2, 2, 3, 3, secondMatch, null);
        Odds thirdOdd = new Odds((long) 3, "Basic", 2, 2, 2, 2, 3, 3, thirdMatch, null);
        Odds fourthOdd = new Odds((long) 4, "Basic", 2, 5, 2, 2, 2, 3, fourthMatch, null);
        Odds fifthOdd = new Odds((long) 5, "Basic", 2, 2, 4, 2, 3, 3, fifthMatch, null);
        Odds sixthOdd = new Odds((long) 6, "Basic", 2, 2, 2, 2, 3, 3, sixthMatch, null);

        ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
        ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
        ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X"));
        ticketOdds.add(new TicketOdds(3, ticket, thirdOdd, (long) 2, ">23.5g"));
        ticketOdds.add(new TicketOdds(4, ticket, fourthOdd, (long) 2, "1X"));
        ticketOdds.add(new TicketOdds(5, ticket, fifthOdd, (long) 3, "1H10"));
        ticketOdds.add(new TicketOdds(6, ticket, sixthOdd, (long) 3, "No Blood"));

        given(oddsService.findOddsById((long) 1)).willReturn(Optional.of(firstOdd));
        given(oddsService.findOddsById((long) 2)).willReturn(Optional.of(secondOdd));
        given(oddsService.findOddsById((long) 3)).willReturn(Optional.of(thirdOdd));
        given(oddsService.findOddsById((long) 4)).willReturn(Optional.of(fourthOdd));
        given(oddsService.findOddsById((long) 5)).willReturn(Optional.of(fifthOdd));
        given(oddsService.findOddsById((long) 6)).willReturn(Optional.of(sixthOdd));

        float totalOdd = ticketOddsService.validateNewTicket(ticketOdds);

        //Allowed difference when comparing two float values, in this case, total odd values.
        final float epsilon = 0.0001f;
        if (Math.abs(144f - totalOdd) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given array of ticket odds with special offer when validating new ticket with not enough bigger odds then not ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenArrayOfTicketOddsWithSpecialOffer_whenValidatingNewTicketWithNotEnoughBiggerOdds_thenNotOk()
      throws Exception {
        result = false;

        Instant matchDate = Instant.now().plusSeconds(60);

        Ticket ticket = new Ticket((long) 1, 4, 684, 0, null, null);

        Types firstTypes = new Types((long) 1, "Football", "1", "X", "2", "1X", "X2", "12", null);
        Types thirdTypes = new Types((long) 3, "Tennis", "1", "2", ">23.5g", "<23.5g", ">=2.5h", "<2.5h", null);
        Types fourthTypes = new Types((long) 4, "Waterpolo", "1", "X", "2", ">15e", "<15e", "Canceled", null);

        Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", firstTypes, null);
        Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", fourthTypes, null);
        Match thirdMatch = new Match((long) 3, matchDate, "Rogger Federer", "Rafael Nadal", thirdTypes, null);
        Match fourthMatch = new Match((long) 4, matchDate, "Hajduk", "Dinamo", firstTypes, null);
        Match fifthMatch = new Match((long) 5, matchDate, "Rijeka", "Osijek", firstTypes, null);
        Match sixthMatch = new Match((long) 6, matchDate, "Solin", "Kastela", firstTypes, null);

        Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
        Odds secondOdd = new Odds((long) 2, "Special Offer", 2, 2, 2, 2, 3, 3, secondMatch, null);
        Odds thirdOdd = new Odds((long) 3, "Basic", 2, 2, 2, 2, 3, 3, thirdMatch, null);
        Odds fourthOdd = new Odds((long) 4, "Basic", 2, 5, 2, 2, 2, 3, fourthMatch, null);
        Odds fifthOdd = new Odds((long) 5, "Basic", 2, 2, 4, 2, 3, 3, fifthMatch, null);
        Odds sixthOdd = new Odds((long) 6, "Basic", 1, 2, 2, 2, 3, 3, sixthMatch, null);

        ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
        ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
        ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X"));
        ticketOdds.add(new TicketOdds(3, ticket, thirdOdd, (long) 2, "2"));
        ticketOdds.add(new TicketOdds(4, ticket, fourthOdd, (long) 2, "1"));
        ticketOdds.add(new TicketOdds(5, ticket, fifthOdd, (long) 3, "X2"));
        ticketOdds.add(new TicketOdds(6, ticket, sixthOdd, (long) 1, "1"));

        given(oddsService.findOddsById((long) 1)).willReturn(Optional.of(firstOdd));
        given(oddsService.findOddsById((long) 2)).willReturn(Optional.of(secondOdd));
        given(oddsService.findOddsById((long) 3)).willReturn(Optional.of(thirdOdd));
        given(oddsService.findOddsById((long) 4)).willReturn(Optional.of(fourthOdd));
        given(oddsService.findOddsById((long) 5)).willReturn(Optional.of(fifthOdd));
        given(oddsService.findOddsById((long) 6)).willReturn(Optional.of(sixthOdd));

        float totalOdd = ticketOddsService.validateNewTicket(ticketOdds);

        //Allowed difference when comparing two float values, in this case, total odd values.
        final float epsilon = 0.0001f;
        if (Math.abs(0f - totalOdd) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given array of ticket odds with two special offers when validating new ticket not ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenArrayOfTicketOddsWithTwoSpecialOffers_whenValidatingNewTicket_thenNotOk()
      throws Exception {
        result = false;

        Instant matchDate = Instant.now().plusSeconds(60);

        Ticket ticket = new Ticket((long) 1, 4, 684, 0, null, null);

        Types firstTypes = new Types((long) 1, "Football", "1", "X", "2", "1X", "X2", "12", null);
        Types thirdTypes = new Types((long) 3, "Tennis", "1", "2", ">23.5g", "<23.5g", ">=2.5h", "<2.5h", null);
        Types fourthTypes = new Types((long) 4, "Waterpolo", "1", "X", "2", ">15e", "<15e", "Canceled", null);

        Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", firstTypes, null);
        Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", fourthTypes, null);
        Match thirdMatch = new Match((long) 3, matchDate, "Rogger Federer", "Rafael Nadal", thirdTypes, null);
        Match fourthMatch = new Match((long) 4, matchDate, "Hajduk", "Dinamo", firstTypes, null);
        Match fifthMatch = new Match((long) 5, matchDate, "Rijeka", "Osijek", firstTypes, null);
        Match sixthMatch = new Match((long) 6, matchDate, "Solin", "Kastela", firstTypes, null);

        Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
        Odds secondOdd = new Odds((long) 2, "Special Offer", 2, 2, 2, 2, 3, 3, secondMatch, null);
        Odds thirdOdd = new Odds((long) 3, "Basic", 2, 2, 2, 2, 3, 3, thirdMatch, null);
        Odds fourthOdd = new Odds((long) 4, "Special Offer", 2, 5, 2, 2, 2, 3, fourthMatch, null);
        Odds fifthOdd = new Odds((long) 5, "Basic", 2, 2, 4, 2, 3, 3, fifthMatch, null);
        Odds sixthOdd = new Odds((long) 6, "Basic", 2, 2, 2, 2, 3, 3, sixthMatch, null);

        ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
        ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
        ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X"));
        ticketOdds.add(new TicketOdds(3, ticket, thirdOdd, (long) 2, "2"));
        ticketOdds.add(new TicketOdds(4, ticket, fourthOdd, (long) 2, "1"));
        ticketOdds.add(new TicketOdds(5, ticket, fifthOdd, (long) 3, "X2"));
        ticketOdds.add(new TicketOdds(6, ticket, sixthOdd, (long) 1, "1"));

        given(oddsService.findOddsById((long) 1)).willReturn(Optional.of(firstOdd));
        given(oddsService.findOddsById((long) 2)).willReturn(Optional.of(secondOdd));
        given(oddsService.findOddsById((long) 3)).willReturn(Optional.of(thirdOdd));
        given(oddsService.findOddsById((long) 4)).willReturn(Optional.of(fourthOdd));
        given(oddsService.findOddsById((long) 5)).willReturn(Optional.of(fifthOdd));
        given(oddsService.findOddsById((long) 6)).willReturn(Optional.of(sixthOdd));

        float totalOdd = ticketOddsService.validateNewTicket(ticketOdds);

        //Allowed difference when comparing two float values, in this case, total odd values.
        final float epsilon = 0.0001f;
        if (Math.abs(0f - totalOdd) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given array of ticket odds with two same matches when validating new ticket not ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenArrayOfTicketOddsWithTwoSameMataches_whenValidatingNewTicket_thenNotOk()
      throws Exception {
        result = false;

        Instant matchDate = Instant.now().plusSeconds(60);

        Ticket ticket = new Ticket((long) 1, 4, 684, 0, null, null);

        Types firstTypes = new Types((long) 1, "Football", "1", "X", "2", "1X", "X2", "12", null);
        Types thirdTypes = new Types((long) 3, "Tennis", "1", "2", ">23.5g", "<23.5g", ">=2.5h", "<2.5h", null);
        Types fourthTypes = new Types((long) 4, "Waterpolo", "1", "X", "2", ">15e", "<15e", "Canceled", null);

        Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", firstTypes, null);
        Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", fourthTypes, null);
        Match thirdMatch = new Match((long) 3, matchDate, "Rogger Federer", "Rafael Nadal", thirdTypes, null);
        Match fourthMatch = new Match((long) 4, matchDate, "Hajduk", "Dinamo", firstTypes, null);
        Match fifthMatch = new Match((long) 5, matchDate, "Rijeka", "Osijek", firstTypes, null);

        Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
        Odds secondOdd = new Odds((long) 2, "Special Offer", 2, 2, 2, 2, 3, 3, secondMatch, null);
        Odds thirdOdd = new Odds((long) 3, "Basic", 2, 2, 2, 2, 3, 3, thirdMatch, null);
        Odds fourthOdd = new Odds((long) 4, "Basic", 2, 5, 2, 2, 2, 3, fourthMatch, null);
        Odds fifthOdd = new Odds((long) 5, "Basic", 2, 2, 4, 2, 3, 3, fifthMatch, null);
        Odds sixthOdd = new Odds((long) 6, "Basic", 2, 2, 2, 2, 3, 3, firstMatch, null);

        ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
        ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));
        ticketOdds.add(new TicketOdds(2, ticket, secondOdd, (long) 2, "X"));
        ticketOdds.add(new TicketOdds(3, ticket, thirdOdd, (long) 2, "2"));
        ticketOdds.add(new TicketOdds(4, ticket, fourthOdd, (long) 2, "1"));
        ticketOdds.add(new TicketOdds(5, ticket, fifthOdd, (long) 3, "X2"));
        ticketOdds.add(new TicketOdds(6, ticket, sixthOdd, (long) 1, "1"));

        given(oddsService.findOddsById((long) 1)).willReturn(Optional.of(firstOdd));
        given(oddsService.findOddsById((long) 2)).willReturn(Optional.of(secondOdd));
        given(oddsService.findOddsById((long) 3)).willReturn(Optional.of(thirdOdd));
        given(oddsService.findOddsById((long) 4)).willReturn(Optional.of(fourthOdd));
        given(oddsService.findOddsById((long) 5)).willReturn(Optional.of(fifthOdd));
        given(oddsService.findOddsById((long) 6)).willReturn(Optional.of(sixthOdd));

        float totalOdd = ticketOddsService.validateNewTicket(ticketOdds);

        //Allowed difference when comparing two float values, in this case, total odd values.
        final float epsilon = 0.0001f;
        if (Math.abs(0f - totalOdd) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given array of ticket odds with two same matches as Special Offer and Basic offer when validating new ticket not ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenArrayOfTicketOddsWithTwoSameMatchesAsSpecialAndBasic_whenValidatingNewTicket_thenNotOk()
      throws Exception {
        result = false;

        Instant matchDate = Instant.now().plusSeconds(60);


        Types firstTypes = new Types((long) 1, "Football", "1", "X", "2", "1X", "X2", "12", null);
        Types thirdTypes = new Types((long) 3, "Tennis", "1", "2", ">23.5g", "<23.5g", ">=2.5h", "<2.5h", null);
        Types fourthTypes = new Types((long) 4, "Waterpolo", "1", "X", "2", ">15e", "<15e", "Canceled", null);

        Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", firstTypes, null);
        Match secondMatch = new Match((long) 2, matchDate, "Jug", "Mladost", fourthTypes, null);
        Match thirdMatch = new Match((long) 3, matchDate, "Rogger Federer", "Rafael Nadal", thirdTypes, null);
        Match fourthMatch = new Match((long) 4, matchDate, "Hajduk", "Dinamo", firstTypes, null);
        Match fifthMatch = new Match((long) 5, matchDate, "Rijeka", "Osijek", firstTypes, null);

        Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
        Odds secondOdd = new Odds((long) 2, "Special Offer", 2, 2, 2, 2, 3, 3, secondMatch, null);
        Odds thirdOdd = new Odds((long) 3, "Basic", 2, 2, 2, 2, 3, 3, thirdMatch, null);
        Odds fourthOdd = new Odds((long) 4, "Basic", 2, 5, 2, 2, 2, 3, fourthMatch, null);
        Odds fifthOdd = new Odds((long) 5, "Basic", 2, 2, 4, 2, 3, 3, fifthMatch, null);
        Odds sixthOdd = new Odds((long) 6, "Basic", 2, 2, 2, 2, 3, 3, secondMatch, null);

        //proof that it works with just received id
        Odds firstOddId = new Odds((long) 1, null, 0, 0, 0, 0, 0, 0, null, null);
        Odds secondOddId = new Odds((long) 2, null, 0, 0, 0, 0, 0, 0, null, null);
        Odds thirdOddId = new Odds((long) 3, null, 0, 0, 0, 0, 0, 0, null, null);
        Odds fourthOddId = new Odds((long) 4, null, 0, 0, 0, 0, 0, 0, null, null);
        Odds fifthOddId = new Odds((long) 5, null, 0, 0, 0, 0, 0, 0, null, null);
        Odds sixthOddId = new Odds((long) 6, null, 0, 0, 0, 0, 0, 0, null, null);
        
        ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
        ticketOdds.add(new TicketOdds(1, null, firstOddId, (long) 2, "1"));
        ticketOdds.add(new TicketOdds(2, null, secondOddId, (long) 2, "X"));
        ticketOdds.add(new TicketOdds(3, null, thirdOddId, (long) 2, "2"));
        ticketOdds.add(new TicketOdds(4, null, fourthOddId, (long) 2, "1"));
        ticketOdds.add(new TicketOdds(5, null, fifthOddId, (long) 3, "X2"));
        ticketOdds.add(new TicketOdds(6, null, sixthOddId, (long) 2, "1"));

        given(oddsService.findOddsById((long) 1)).willReturn(Optional.of(firstOdd));
        given(oddsService.findOddsById((long) 2)).willReturn(Optional.of(secondOdd));
        given(oddsService.findOddsById((long) 3)).willReturn(Optional.of(thirdOdd));
        given(oddsService.findOddsById((long) 4)).willReturn(Optional.of(fourthOdd));
        given(oddsService.findOddsById((long) 5)).willReturn(Optional.of(fifthOdd));
        given(oddsService.findOddsById((long) 6)).willReturn(Optional.of(sixthOdd));

        float totalOdd = ticketOddsService.validateNewTicket(ticketOdds);

        //Allowed difference when comparing two float values, in this case, total odd values.
        final float epsilon = 0.0001f;
        if (Math.abs(0f - totalOdd) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given array of ticket odds with with outdated match when validating new ticket not ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenArrayOfTicketOddsWithOutdatedMatch_whenValidatingNewTicket_thenNotOk()
      throws Exception {
        result = false;

        Instant matchDate = Instant.now().minusSeconds(60);

        Ticket ticket = new Ticket((long) 1, 4, 684, 0, null, null);
        Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", null, null);
        Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
        ArrayList<TicketOdds> ticketOdds = new ArrayList<TicketOdds>();
        ticketOdds.add(new TicketOdds(1, ticket, firstOdd, (long) 2, "1"));

        given(oddsService.findOddsById((long) 1)).willReturn(Optional.of(firstOdd));

        float totalOdd = ticketOddsService.validateNewTicket(ticketOdds);

        //Allowed difference when comparing two float values, in this case, total odd values.
        final float epsilon = 0.0001f;
        if (Math.abs(0f - totalOdd) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }

    /**
     * Given received correct pair of ticket odds and odds from database compareDBOddsWithReceivedOdds then ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenCorrectPairOfTicketOddsType1_whencompareDBOddsWithReceivedOdds_thenOk()
      throws Exception {
        result = false;

        Instant matchDate = Instant.now().minusSeconds(60);

        Types firstTypes = new Types((long) 1, "Football", "1", "X", "2", "1X", "X2", "12", null);
        Ticket ticket = new Ticket((long) 1, 4, 684, 0, null, null);
        Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", firstTypes, null);
        Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
        TicketOdds ticketOdd = new TicketOdds(1, ticket, firstOdd, (long) 2, "1");

        boolean result = ticketOddsService.compareDBOddsWithReceivedOdds(ticketOdd, firstOdd);
        assertEquals(true, result);
    }

    /**
     * Given received incorrect pair of ticket odds and odds from database compareDBOddsWithReceivedOdds then not ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void givenIncorrectPairOfTicketOddsType1_whencompareDBOddsWithReceivedOdds_thenNotOk()
      throws Exception {
        result = false;

        Instant matchDate = Instant.now().minusSeconds(60);

        Types firstTypes = new Types((long) 1, "Football", "1", "X", "2", "1X", "X2", "12", null);
        Ticket ticket = new Ticket((long) 1, 4, 684, 0, null, null);
        Match firstMatch = new Match((long) 1, matchDate, "FC Barcelona", "C.F. Real Madrid", firstTypes, null);
        Odds firstOdd = new Odds((long) 1, "Basic", 2, 3, 2, 4, 2, 4, firstMatch, null);
        TicketOdds ticketOdd = new TicketOdds(1, ticket, firstOdd, (long) 2, "12");

        boolean result = ticketOddsService.compareDBOddsWithReceivedOdds(ticketOdd, firstOdd);
        assertEquals(false, result);
    }

}
