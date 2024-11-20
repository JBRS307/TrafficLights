package pl.jbrs.traffic.model.road;

import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.TrafficLight;

import java.util.List;
import java.util.Map;

public class PrioritizedRoad extends BasicRoad {
    protected final int priority;
    public PrioritizedRoad(RoadDirection direction, Map<LaneDirection, List<TrafficLight>> trafficLights, int priority) {
        super(direction, trafficLights);
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }


}
