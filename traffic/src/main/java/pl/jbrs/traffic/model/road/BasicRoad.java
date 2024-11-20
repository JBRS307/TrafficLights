package pl.jbrs.traffic.model.road;

import pl.jbrs.traffic.model.*;
import pl.jbrs.traffic.simulation.configuration.DefaultRoadConfiguration;

import java.util.List;
import java.util.Map;

// Abstract class for road, contains common attributes and default implementations
public class BasicRoad implements Road {
    // Direction tells us about which road this is
    protected final RoadDirection direction;

    protected final Map<LaneDirection, List<TrafficLight>> trafficLights;

    public BasicRoad(RoadDirection direction, Map<LaneDirection, List<TrafficLight>> trafficLights) {
        this.direction = direction;
        this.trafficLights = trafficLights;
    }

    protected Lane getBestLane(List<LaneDirection> possibleLanes) {
        Lane bestLane = null;
        int minWaitingCars = Integer.MAX_VALUE;
        for (LaneDirection laneDirection : possibleLanes) {
            for (TrafficLight trafficLight : trafficLights.get(laneDirection).reversed()) { // It's reversed to start at the rightmost lane
                int waitingCars = trafficLight.getWaitingCars();
                if (waitingCars < minWaitingCars) {
                    minWaitingCars = waitingCars;
                    bestLane = trafficLight.getLane();
                }
            }
        }
        return bestLane;
    }

    @Override
    public void addVehicle(Vehicle v) {
        List<LaneDirection> possibleLanes = LaneDirection.fromMoveDirection(MoveDirection.calcDirection(v.startRoad(), v.endRoad()));
        getBestLane(possibleLanes).addVehicle(v);
    }

    @Override
    public boolean hasCrosswalk() {
        return false;
    }

    @Override
    public PedestrianLight getPedestrianLight() {
        return null;
    }

    @Override
    public int getPriority() {
        return DefaultRoadConfiguration.PRIORITY;
    }

    @Override
    public RoadDirection getDirection() {
        return direction;
    }

    @Override
    public Map<LaneDirection, List<TrafficLight>> getLights() {
        return trafficLights;
    }
}
