package pl.jbrs.traffic.model.road;

import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.PedestrianLight;
import pl.jbrs.traffic.model.TrafficLight;

import java.util.List;
import java.util.Map;

public class PrioritizedPedestrianRoad extends PrioritizedRoad {
    private final PedestrianLight pedestrianLight;

    public PrioritizedPedestrianRoad(RoadDirection direction, Map<LaneDirection, List<TrafficLight>> trafficLights, int priority, PedestrianLight pedestrianLight) {
        super(direction, trafficLights, priority);
        this.pedestrianLight = pedestrianLight;
    }

    @Override
    public boolean hasCrosswalk() {
        return true;
    }

    @Override
    public PedestrianLight getPedestrianLight() {
        return pedestrianLight;
    }
}
