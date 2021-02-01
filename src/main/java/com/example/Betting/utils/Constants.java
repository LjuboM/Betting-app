package com.example.Betting.utils;

/**
 * The Class Constants used to store Constants.
 * These constants can be used anyway in Betting app.
 * This approach simplifies changing of these global variables.
 */
public final class Constants {

    /**
     * Odds bigger than this value are considered bigger.
     */
    public static final float BIGGER_ODD = 1.10f;

    /**
     * Represents needed basic bigger odds to be combined with Special odd.
     */
    public static final int NUMBER_OF_BIGGER_ODDS = 5;

    /**
     * Value close to 0 which is used in comparing float values.
     */
    public static final float EPSILON = 0.00001f;

    /**
     * Constructor.
     */
    private Constants() {
     }
}
