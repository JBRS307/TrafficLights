package pl.jbrs.traffic.simulation;

public class Configuration {

    private final int stateLength;

    public Configuration(int stateLength) {
        this.stateLength = stateLength;
    }

    // Empty constructor containing default values
    public Configuration() {
        this.stateLength = DefaultConfiguration.STATE_LENGTH;
    }

}
