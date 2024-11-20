package pl.jbrs.traffic.configuration;

import pl.jbrs.traffic.exception.InvalidRoadConfigurationException;
import pl.jbrs.traffic.model.LaneDirection;

import java.util.Map;

public class RoadConfiguration {
    private Map<LaneDirection, Integer> lanes;
    private boolean crosswalk;
    private int priority;

    public RoadConfiguration() {
        this.lanes = DefaultRoadConfiguration.defaultLanes();
        this.crosswalk = DefaultRoadConfiguration.CROSSWALK;
        this.priority = DefaultRoadConfiguration.PRIORITY;
    }

    public Map<LaneDirection, Integer> getLanes() {
        return lanes;
    }

    public boolean hasCrosswalk() {
        return crosswalk;
    }

    public void setCrosswalk(boolean crosswalk) {
        this.crosswalk = crosswalk;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < 1) {
            throw new IllegalArgumentException("Priority must be greater than 0");
        }
        this.priority = priority;
    }

    public void validate() throws InvalidRoadConfigurationException {
        if (lanes.get(LaneDirection.UTURN) > 1 || lanes.get(LaneDirection.UTURN_LEFT) > 1) {
            throw new InvalidRoadConfigurationException("Road contains more than one uturn lane!");
        }

        if (lanes.containsKey(LaneDirection.UTURN_LEFT) && lanes.containsKey(LaneDirection.UTURN)) {
            throw new InvalidRoadConfigurationException("Road contains uturnLeft and uturn lanes at the same time!");
        }
    }


}
