package pl.jbrs.traffic.model.road;

import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.PedestrianLight;
import pl.jbrs.traffic.model.TrafficLight;

import java.util.List;
import java.util.Map;

public class PedestrianRoad extends BasicRoad {
    private final PedestrianLight pedestrianLight;
    public PedestrianRoad(RoadDirection direction, Map<LaneDirection, List<TrafficLight>> trafficLights, PedestrianLight pedestrianLight) {
        super(direction, trafficLights);
        this.pedestrianLight = pedestrianLight;
    }

    @Override
    public PedestrianLight getPedestrianLight() {
        return pedestrianLight;
    }

    @Override
    public boolean hasCrosswalk() {
        return true;
    }
}
