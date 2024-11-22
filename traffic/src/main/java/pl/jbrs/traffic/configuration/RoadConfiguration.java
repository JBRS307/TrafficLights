package pl.jbrs.traffic.configuration;

import org.json.JSONException;
import org.json.JSONObject;
import pl.jbrs.traffic.exception.InvalidRoadConfigurationException;
import pl.jbrs.traffic.jsonparser.options.RoadOption;
import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RoadConfiguration {
    private Map<LaneDirection, Integer> lanes;
    private boolean crosswalk;
    private int priority;

    public RoadConfiguration() {
        this.lanes = DefaultRoadConfiguration.defaultLanes();
        this.crosswalk = DefaultRoadConfiguration.CROSSWALK;
        this.priority = DefaultRoadConfiguration.PRIORITY;
    }

    public static Map<RoadDirection, RoadConfiguration> fromJSON(JSONObject json) {
        Map<RoadDirection, RoadConfiguration> confMap = new HashMap<>();
        for (RoadDirection direction : RoadDirection.values()) {
            RoadConfiguration conf = new RoadConfiguration();
            JSONObject road;
            try {
                road = json.getJSONObject(direction.toString());
            } catch (JSONException e) {
                confMap.put(direction, conf);
                continue;
            }

            try {
                conf.setPriority(road.getInt(RoadOption.Priority.toString()));
            } catch (JSONException ignored) {
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }

            try {
                conf.setCrosswalk(road.getBoolean(RoadOption.Crosswalk.toString()));
            } catch (JSONException ignored) {}

            JSONObject lanes;
            try {
                lanes = road.getJSONObject(RoadOption.Lanes.toString());
            } catch (JSONException e) {
                continue;
            }

            for (LaneDirection laneDirection : LaneDirection.values()) {
                try {
                    conf.putToLanes(laneDirection, lanes.getInt(laneDirection.toString()));
                } catch (JSONException ignored) {
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            confMap.put(direction, conf);
        }
        return confMap;
    }

    public Map<LaneDirection, Integer> getLanes() {
        return lanes;
    }

    public void putToLanes(LaneDirection direction, int lanesNumber) {
        if (lanesNumber < 0) {
            throw new IllegalArgumentException("Number of lanes cannot be negative");
        }
        lanes.put(direction, lanesNumber);
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
        this.priority = priority;
    }

    public void validate() throws InvalidRoadConfigurationException {
        if ((lanes.containsKey(LaneDirection.UTURN) && lanes.get(LaneDirection.UTURN) > 1) ||
            (lanes.containsKey(LaneDirection.UTURN_LEFT) && lanes.get(LaneDirection.UTURN_LEFT) > 1)) {
            throw new InvalidRoadConfigurationException("Road contains more than one uturn lane!");
        }

        if (lanes.containsKey(LaneDirection.UTURN_LEFT) && lanes.containsKey(LaneDirection.UTURN)) {
            throw new InvalidRoadConfigurationException("Road contains uturnLeft and uturn lanes at the same time!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoadConfiguration that = (RoadConfiguration) o;
        return crosswalk == that.crosswalk && priority == that.priority && Objects.equals(lanes, that.lanes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lanes, crosswalk, priority);
    }
}
