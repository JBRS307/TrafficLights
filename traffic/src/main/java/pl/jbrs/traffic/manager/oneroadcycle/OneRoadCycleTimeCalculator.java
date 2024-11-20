package pl.jbrs.traffic.manager.oneroadcycle;

import pl.jbrs.traffic.model.TrafficLight;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.simulation.Configuration;

import java.util.List;
import java.util.Map;

public class OneRoadCycleTimeCalculator {
    private final Configuration config;

    public OneRoadCycleTimeCalculator(Configuration config) {
        this.config = config;
    }

    public int calcNextStepLength(Map<RoadDirection, Road> roadMap, OneRoadCycleState currentState) {
        if (currentState.isYellow()) {
            return config.getYellowTime();
        }

        int stateCars = 0;
        int restCars = 0;

        RoadDirection currentDirection = currentState.next().stateToDirection();

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
        return Math.round((float)stateCars / restCars) * config.getStateLength();
    }
}
