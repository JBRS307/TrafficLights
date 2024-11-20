package pl.jbrs.traffic.simulation.configuration;

public class DefaultModelConfiguration {
    // Base value of determining how many simulation steps should strategy state take
    // It is adjusted by strategy
    public static final int STATE_LENGTH = 30;

    // Determines how many cars is worth 1 priority point
    public static final int PRIORITY_MULTIPLIER = 10;

    // This should rather stay default
    public static final int YELLOW_TIME = 3;

    public static final int MIN_STATE_LENGTH = 5;
}
