package pl.jbrs.traffic.configuration;

import org.json.JSONException;
import org.json.JSONObject;
import pl.jbrs.traffic.json.parser.configoptions.ModelOption;

public class ModelConfiguration {
    private int stateLength;
    private int priorityMultiplier;
    private int yellowTime;

    private int minStateLength;

    // Empty constructor containing default values
    public ModelConfiguration() {
        this.stateLength = DefaultModelConfiguration.STATE_LENGTH;
        this.priorityMultiplier = DefaultModelConfiguration.PRIORITY_MULTIPLIER;
        this.yellowTime = DefaultModelConfiguration.YELLOW_TIME;
        this.minStateLength = DefaultModelConfiguration.MIN_STATE_LENGTH;
    }

    public static ModelConfiguration fromJSON(JSONObject json) {
        ModelConfiguration modelConfiguration = new ModelConfiguration();

        // All of the fields must be separately checked.
        // If key doesn't exist, or type of value is invalid, then
        // the config entry is ignored
        try {
            modelConfiguration.setStateLength(json.getInt(ModelOption.StateLength.toString()));
        } catch (JSONException ignored) {
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.out.println("State length not set, default value kept!");
        }

        try {
            modelConfiguration.setPriorityMultiplier(json.getInt(ModelOption.PriorityMultiplier.toString()));
        } catch (JSONException ignored) {
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.out.println("Priority multiplier not set, default value kept!");
        }

        try {
            modelConfiguration.setYellowTime(json.getInt(ModelOption.YellowTime.toString()));
        } catch (JSONException ignored) {
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.out.println("Yellow time not set, default value kept!");
        }

        try {
            modelConfiguration.setMinStateLength(json.getInt(ModelOption.MinStateLength.toString()));
        } catch (JSONException ignored) {
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.out.println("Minimal state length not set, default value kept");
        }

        return modelConfiguration;
    }

    public int getStateLength() {
        return stateLength;
    }

    public void setStateLength(int stateLength) {
        if (stateLength < DefaultModelConfiguration.STATE_LENGTH_LOWER_LIMIT) {
            throw new IllegalArgumentException("stateLength must be greater than " + DefaultModelConfiguration.STATE_LENGTH_LOWER_LIMIT);
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
        if (minStateLength < DefaultModelConfiguration.STATE_LENGTH_LOWER_LIMIT) {
            throw new IllegalArgumentException("minStateLength must be greater than " + DefaultModelConfiguration.STATE_LENGTH_LOWER_LIMIT);
        }
        this.minStateLength = minStateLength;
    }

}
