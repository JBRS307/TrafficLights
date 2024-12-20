package pl.jbrs.traffic.configuration;

import pl.jbrs.traffic.model.LaneDirection;

import java.util.HashMap;
import java.util.Map;

public class DefaultRoadConfiguration {
    public static final boolean CROSSWALK = false;
    public static final int PRIORITY = 0;

    public static Map<LaneDirection, Integer> defaultLanes() {
        Map<LaneDirection, Integer> lanes = new HashMap<>();
        lanes.put(LaneDirection.UTURN_LEFT, 1);
        lanes.put(LaneDirection.STRAIGHT, 1);
        lanes.put(LaneDirection.RIGHT, 1);
        return lanes;
    }
}
