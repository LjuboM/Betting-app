package com.example.Betting.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Betting.model.Odds;
import com.example.Betting.model.Ticket;
import com.example.Betting.model.TicketOdds;
import com.example.Betting.repository.TicketOddsRepository;

/**
 * The Class TicketOddsService.
 */
@Service
public class TicketOddsService implements ITicketOddsService {

	/** The ticket odds repository. */
	@Autowired
	private TicketOddsRepository ticketOddsRepository;

	 /** The ticket repository. */
    @Autowired
    private TicketOddsRepository ticketRepository;

	/** The odds service. */
    @Autowired
    private OddsService oddsService;

	/**
	 * Find all played ticket pairs.
	 *
	 * @return the collection of ticket odds
	 */
	public Collection<TicketOdds> findAllPlayedTicketPairs() {
		return ticketOddsRepository.findAll(Sort.by(Sort.Direction.DESC, "odds.match.matchdate"));
	}

	/**
	 * Creates the ticket odds pair.
	 *
	 * @param ticketOdd the ticket odd
	 */
	public void createTicketOddsPair(final TicketOdds ticketOdd) {
		ticketOddsRepository.save(ticketOdd);
	}

	/**
	 * Find all played pairs by ticket id.
	 *
	 * @param ticketId the ticket id
	 * @return the collection of ticket odds
	 */
	public Collection<TicketOdds> findAllPlayedPairsByTicketId(final long ticketId) {
		return ticketOddsRepository.findAllByTicketId(ticketId);
	}

	 /**
     * Find all played pairs by ticket id.
     *
     * @param totalOdd the total odd of the ticket
     * @param spentMoney the bet money
     * @return the collection of ticket odds
     */
	public Ticket createTicket(final float totalOdd, final float spentMoney) {
	    final float withoutManipulativeSpends = 0.95f;
	    Ticket ticket = new Ticket();
	    // Status 0 -> Waiting for results.
	    ticket.setStatus(0);
	    ticket.setTotalodd(totalOdd);
	    float possibleGainWithoutTaxes = withoutManipulativeSpends * totalOdd * spentMoney;
	    float taxes = calculateTicketTaxes(possibleGainWithoutTaxes);
	    ticket.setPossiblegain(possibleGainWithoutTaxes - taxes);

        ticketRepository.save(ticket);
        return ticket;
	}

    /**
     * Validates new ticket.
     *
     * @param ticketOdds the ticket odds array
     * @return Total odd value
     */
    public float validateNewTicket(final ArrayList<TicketOdds> ticketOdds) {
        /** Minimum number of basic odds higher than minOddValue needed for valid ticket */
        final int minBiggerOddsCount = 5;
        final float minOddValue = (float) 1.10;
        float totalOdd = 1;
        float[] oddValues = new float[ticketOdds.size()];
        Long[] matches = new Long[ticketOdds.size()];
        boolean specialOffer = false;
        long specialOfferMatch = 1;
        int iterator = 0;
        Instant currentTime = Instant.now();

        for (TicketOdds ticketOdd : ticketOdds) {
            final Odds odds = oddsService.findOddsById(ticketOdd.getOdds().getId()).get();
            matches[iterator] = odds.getMatch().getId();
            totalOdd = totalOdd * ticketOdd.getOdd();
            if (odds.getType().equals("Basic")) {
                oddValues[iterator] = ticketOdd.getOdd();
            } else if (!specialOffer) {
                specialOffer = true;
                specialOfferMatch = odds.getMatch().getId();
                oddValues[iterator] = (float) 1.0;
            } else {
                System.out.println("Invalid bet, more than one Special offer played!");
                return 0f;
            }
            if (odds.getMatch().getMatchdate().compareTo(currentTime) < 1) {
                System.out.println("Invalid bet, too late, match already started!");
                return 0f;
            }
            if (!compareDBOddsWithReceivedOdds(ticketOdd, odds)) {
                System.out.println("Invalid bet, that odd value is not valid!");
                return 0f;
            }
            iterator++;
        }

        int specialOfferMatchOccurrences = 0;
        //Number of odds bigger than minOddValue
        int biggerOddsCount = 0;
        int matchesIterator = 0;
        if (specialOffer) {
            for (Long match : matches) {
                if (specialOfferMatch == match) {
                    specialOfferMatchOccurrences++;
                }
                if (oddValues[matchesIterator] >= minOddValue) {
                    biggerOddsCount++;
                }
                matchesIterator++;
            }
            if (specialOfferMatchOccurrences > 1) {
                System.out.println("Invalid bet, played the same match in special offer and basic type!");
                return 0f;
            }
            if (biggerOddsCount < minBiggerOddsCount) {
                System.out.println("Invalid bet, you have to play at least "
                        + String.valueOf(minBiggerOddsCount) + " basic odds that are "
                        + String.valueOf(minOddValue) + " or bigger!");
                return 0f;
            }
        }

        return totalOdd;
    }

    /**
     * Validates received odds by comparing them with the database odds.
     *
     * @param ticketOdd the ticket odds pair received from user
     * @param odds the odds from DataBase
     * @return true for finding right odd and type combination in the database, otherwise false.
     */
    private boolean compareDBOddsWithReceivedOdds(final TicketOdds ticketOdd, final Odds odds) {
        //Allowed difference when comparing 2 float values, in this case, odd values.
        final float epsilon = 0.0001f;
        final String type = ticketOdd.getType();
        final float odd = ticketOdd.getOdd();

        if (type.equals(odds.getMatch().getTypes().getType1()) && Math.abs(odd - odds.getOdd1()) < epsilon) {
            return true;
        } else if (type.equals(odds.getMatch().getTypes().getType2()) && Math.abs(odd - odds.getOdd2()) < epsilon) {
            return true;
        } else if (type.equals(odds.getMatch().getTypes().getType3()) && Math.abs(odd - odds.getOdd3()) < epsilon) {
            return true;
        } else if (type.equals(odds.getMatch().getTypes().getType4()) && Math.abs(odd - odds.getOdd4()) < epsilon) {
            return true;
        } else if (type.equals(odds.getMatch().getTypes().getType5()) && Math.abs(odd - odds.getOdd5()) < epsilon) {
            return true;
        } else if (type.equals(odds.getMatch().getTypes().getType6()) && Math.abs(odd - odds.getOdd6()) < epsilon) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the taxes for the betting transaction.
     * First 10000 HRK is taxed by 10%, next 20000 by 15%, next 470000 by 20% and the rest by 30%
     *
     * @param money the money
     * @return taxes
     */
    float calculateTicketTaxes(final float money) {
        final float moneyForTenPercentageTax = 10000f;
        final float moneyForFifteenPercentageTax = 20000f;
        final float moneyForTwentyPercentageTax = 470000f;
        final float tenPercentage = 0.10f;
        final float fifteenPercentage = 0.15f;
        final float twentyPercentage = 0.20f;
        final float thirtyPercentage = 0.30f;
        final float fullyTaxedTenPercentage = 1000;
        final float fullyTaxedFifteenPercentage = 4000;
        final float fullyTaxedTwentyPercentage = 94000;
        float newTax = 0;
        float possibleGainparts = money;
        //if true, fully tax first 10 000
        if (possibleGainparts >= moneyForTenPercentageTax) {
          newTax = fullyTaxedTenPercentage;
          possibleGainparts = possibleGainparts - moneyForTenPercentageTax;
          //if true, fully tax 20 000
          if (possibleGainparts >= moneyForFifteenPercentageTax) {
            newTax = fullyTaxedFifteenPercentage;
            possibleGainparts = possibleGainparts - moneyForFifteenPercentageTax;
            //if true, fully tax 470000
            if (possibleGainparts >= moneyForTwentyPercentageTax) {
              newTax = fullyTaxedTwentyPercentage;
              possibleGainparts = possibleGainparts - moneyForTwentyPercentageTax;
              //if true fully tax the rest
              if (possibleGainparts > 0) {
                newTax = newTax + possibleGainparts * thirtyPercentage;
              }
            } else {
              newTax = newTax + possibleGainparts * twentyPercentage;
            }
          } else {
            newTax = newTax + possibleGainparts * fifteenPercentage;
          }
        } else {
          newTax = possibleGainparts * tenPercentage;
        }
        return newTax;
    }

}

