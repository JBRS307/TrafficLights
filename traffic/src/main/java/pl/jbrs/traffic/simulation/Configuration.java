package pl.jbrs.traffic.simulation;

public class Configuration {
    private int stateLength;
    private int priorityMultiplier;
    private int yellowTime;

    private int minStateLength;

    // Empty constructor containing default values
    public Configuration() {
        this.stateLength = DefaultConfiguration.STATE_LENGTH;
        this.priorityMultiplier = DefaultConfiguration.PRIORITY_MULTIPLIER;
        this.yellowTime = DefaultConfiguration.YELLOW_TIME;
        this.minStateLength = DefaultConfiguration.MIN_STATE_LENGTH;
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

    public int getYellowTime() {
        return yellowTime;
    }

    public void setYellowTime(int yellowTime) {
        if (yellowTime < 1) {
            throw new IllegalArgumentException("yellowTime must be greater than 0");
        }
        this.yellowTime = yellowTime;
    }

    public int getMinStateLength() {
        return minStateLength;
    }

    public void setMinStateLength(int minStateLength) {
        if (minStateLength < 1) {
            throw new IllegalArgumentException("minStateLength must be greater than 0");
        }
        this.minStateLength = minStateLength;
    }

}
