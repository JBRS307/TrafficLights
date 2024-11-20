package pl.jbrs.traffic.model.road;

import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.TrafficLight;

import java.util.List;
import java.util.Map;

public class BasicRoad extends AbstractRoad {
    public BasicRoad(RoadDirection direction, Map<LaneDirection, List<TrafficLight>> trafficLights, int priority) {
        super(direction, trafficLights, priority);
    }
}
