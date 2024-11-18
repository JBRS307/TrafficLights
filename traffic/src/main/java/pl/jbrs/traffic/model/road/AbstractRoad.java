package pl.jbrs.traffic.model.road;

import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.TrafficLight;
import pl.jbrs.traffic.model.Vehicle;

import java.util.List;
import java.util.Map;

public abstract class AbstractRoad implements Road {
    // Direction tells us about which road this is
    protected final RoadDirection direction;

    protected final Map<LaneDirection, List<TrafficLight>> trafficLights;

    public AbstractRoad(RoadDirection direction, Map<LaneDirection, List<TrafficLight>> trafficLights) {
        this.direction = direction;
        this.trafficLights = trafficLights;
    }

    @Override
    public void addVehicle(Vehicle v) {
        
    }


}
