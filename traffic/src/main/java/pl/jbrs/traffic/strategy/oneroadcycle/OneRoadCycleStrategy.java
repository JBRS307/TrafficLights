package pl.jbrs.traffic.strategy.oneroadcycle;

import pl.jbrs.traffic.model.TrafficLight;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.simulation.Configuration;
import pl.jbrs.traffic.strategy.Strategy;

import java.util.List;
import java.util.Map;

// Most basic strategy possible
// Every road is allowed to go in 4 step cycle
// If there is crosswalk on any road then pedestrians are allowed to cross
// whenever the lane on their left has green light (situation when drivers turning right
// and pedestrians have a collision is common on real life crosswalks)
public class OneRoadCycleStrategy implements Strategy {
    private final Configuration config;
    private OneRoadCycleState currentState = OneRoadCycleState.NORTH;
    private final Map<RoadDirection, Road> roadMap;

    public OneRoadCycleStrategy(Configuration config, Map<RoadDirection, Road> roadMap) {
        this.config = config;
        this.roadMap = roadMap;
    }

    private RoadDirection stateToDirection(OneRoadCycleState state) {
        return switch (state) {
            case NORTH -> RoadDirection.NORTH;
            case SOUTH -> RoadDirection.SOUTH;
            case EAST -> RoadDirection.EAST;
            case WEST -> RoadDirection.WEST;
        };
    }

    private int calcTime(OneRoadCycleState state) {
        int stateCars = 0;
        int restCars = 0;

        RoadDirection currentDirection = stateToDirection(state);

        for (RoadDirection roadDirection : roadMap.keySet()) {
            int sumOfCars = roadMap.get(roadDirection)
                    .getLights()
                    .values()
                    .stream()
                    .flatMap(List::stream)
                    .mapToInt(TrafficLight::getWaitingCars)
                    .sum();
            sumOfCars += roadMap.get(roadDirection).getPriority() * config.getPriorityMultiplier();
            if (roadDirection == currentDirection) {
                stateCars += sumOfCars;
            } else {
                restCars += sumOfCars;
            }
        }
        restCars = Math.round(restCars / 3.0f);

        return Math.round((float)stateCars / restCars);
    }

    private void enterState(OneRoadCycleState state) {
        RoadDirection currentDirection = stateToDirection(state);
    }

    @Override
    public int calcNextStateTime() {
        return 0;
    }
}
