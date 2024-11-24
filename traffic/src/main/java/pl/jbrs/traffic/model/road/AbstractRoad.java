package pl.jbrs.traffic.model.road;

import pl.jbrs.traffic.exception.ImpossibleMoveException;
import pl.jbrs.traffic.exception.NoCrosswalkException;
import pl.jbrs.traffic.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// Abstract class for road, contains common attributes and default implementations
public abstract class AbstractRoad implements Road {
    // Direction tells us about which road this is
    protected final RoadDirection direction;

    protected final Map<LaneDirection, List<TrafficLight>> trafficLights;

    protected final int priority;

    public AbstractRoad(RoadDirection direction, Map<LaneDirection, List<TrafficLight>> trafficLights, int priority) {
        this.direction = direction;
        this.trafficLights = trafficLights;
        this.priority = priority;
    }

    protected Optional<Lane> getBestLane(List<LaneDirection> possibleLanes) {
        Lane bestLane = null;
        int minWaitingCars = Integer.MAX_VALUE;
        for (LaneDirection laneDirection : possibleLanes) {
            if (!trafficLights.containsKey(laneDirection)) {
                continue;
            }
            for (TrafficLight trafficLight : trafficLights.get(laneDirection).reversed()) { // It's reversed to start at the rightmost lane
                int waitingCars = trafficLight.getWaitingCars();
                if (waitingCars < minWaitingCars) {
                    minWaitingCars = waitingCars;
                    bestLane = trafficLight.getLane();
                }
            }
        }
        return Optional.ofNullable(bestLane);
    }

    @Override
    public void addVehicle(Vehicle v) {
        List<LaneDirection> possibleLanes = LaneDirection.fromMoveDirection(MoveDirection.calcDirection(v.startRoad(), v.endRoad()));
        Optional<Lane> bestLaneOptional = getBestLane(possibleLanes);
        if (bestLaneOptional.isPresent()) {
            bestLaneOptional.get().addVehicle(v);
        } else {
            throw new ImpossibleMoveException("Vehicle " + v.id() + " tries to make impossible move!");
        }
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
        return priority;
    }

    @Override
    public RoadDirection getDirection() {
        return direction;
    }

    @Override
    public Map<LaneDirection, List<TrafficLight>> getLights() {
        return trafficLights;
    }

    @Override
    public boolean isPedestrianCrossing() {
        return false;
    }

    @Override
    public void movePedestrians() {
        throw new NoCrosswalkException("There is no crosswalk on " + direction + " road");
    }

    @Override
    public void addPedestrian() {
        throw new NoCrosswalkException("There is no crosswalk on " + direction + " road");
    }
}
