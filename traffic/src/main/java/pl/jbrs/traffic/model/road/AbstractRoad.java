package pl.jbrs.traffic.model.road;

import pl.jbrs.traffic.model.*;

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

    protected Lane getLane(List<LaneDirection> possibleLanes) {
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
        getLane(possibleLanes).addVehicle(v);
    }

    @Override
    public boolean hasCrosswalk() {
        return false;
    }

    @Override
    public PedestrianLight getPedestrianLight() {
        return null;
    }
}
