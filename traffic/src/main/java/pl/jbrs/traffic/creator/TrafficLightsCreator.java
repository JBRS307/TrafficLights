package pl.jbrs.traffic.creator;

import pl.jbrs.traffic.model.Lane;
import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.TrafficLight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrafficLightsCreator {
    private final Map<LaneDirection, Integer> lanes;

    public TrafficLightsCreator(Map<LaneDirection, Integer> lanes) {
        this.lanes = lanes;
    }

    private Map<LaneDirection, List<Lane>> createLanes() {
        Map<LaneDirection, List<Lane>> createdLanes = new HashMap<>();
        for (LaneDirection direction : lanes.keySet()) {
            int n = lanes.get(direction);
            List<Lane> laneList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                laneList.add(new Lane(direction));
            }
            createdLanes.put(direction, laneList);
        }
        return createdLanes;
    }

    public Map<LaneDirection, List<TrafficLight>> createTrafficLights() {
        Map<LaneDirection, List<Lane>> createdLanes = createLanes();

        Map<LaneDirection, List<TrafficLight>> trafficLights = new HashMap<>();

        for (LaneDirection direction : lanes.keySet()) {
            trafficLights.put(direction, createdLanes
                    .get(direction)
                    .stream()
                    .map(TrafficLight::new)
                    .toList());
        }
        return trafficLights;
    }


}
