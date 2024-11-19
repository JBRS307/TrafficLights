package pl.jbrs.traffic.simulation;

public class DefaultConfiguration {
    // Base value of determining how many simulation steps should strategy state take
    // It is adjusted by strategy
    public static final int STATE_LENGTH = 30;

    // Determines how many cars is worth 1 priority point
    public static final int PRIORITY_MULTIPLIER = 10;
}
