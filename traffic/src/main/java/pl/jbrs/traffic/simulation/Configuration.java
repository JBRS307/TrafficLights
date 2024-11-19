package pl.jbrs.traffic.simulation;

public class Configuration {
    private int stateLength;
    private int priorityMultiplier;

    // Empty constructor containing default values
    public Configuration() {
        this.stateLength = DefaultConfiguration.STATE_LENGTH;
        this.priorityMultiplier = DefaultConfiguration.PRIORITY_MULTIPLIER;
    }

    public int getStateLength() {
        return stateLength;
    }

    public void setStateLength(int stateLength) {
        if (stateLength < 1) {
            throw new IllegalArgumentException("stateLength must be greater than 0");
        }
        this.stateLength = stateLength;
    }

    public int getPriorityMultiplier() {
        return priorityMultiplier;
    }

    public void setPriorityMultiplier(int priorityMultiplier) {
        if (priorityMultiplier < 1) {
            throw new IllegalArgumentException("priorityMultiplier must be greater than 0");
        }
        this.priorityMultiplier = priorityMultiplier;
    }

}
