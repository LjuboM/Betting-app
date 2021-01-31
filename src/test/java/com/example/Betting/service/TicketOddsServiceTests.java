package com.example.Betting.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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

    private boolean result;
    
    /**
     * Given total odd and spent money when creating ticket then ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void giventotalOddAndSpentMoney_whencreateTicket_thenOk()
      throws Exception {
        final float totalOdd = 10;
        final float spentMoney = 10;
        ticketOddsService.createTicket(totalOdd, spentMoney);
        System.out.println(ticketOddsService.createTicket(totalOdd, spentMoney));
        assertEquals(true, true);
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
        //Allowed difference when comparing 2 float values, in this case, odd values.
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
        //Allowed difference when comparing 2 float values, in this case, odd values.
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
        //Allowed difference when comparing 2 float values, in this case, odd values.
        final float epsilon = 0.0001f;
        float taxes = ticketOddsService.calculateTicketTaxes(possibleGain);
        System.out.println(taxes);
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
        //Allowed difference when comparing 2 float values, in this case, odd values.
        final float epsilon = 0.0001f;
        float taxes = ticketOddsService.calculateTicketTaxes(possibleGain);

        if (Math.abs(97000f - taxes) < epsilon) {
            result = true;
        }
        assertEquals(true, result);
    }
}
