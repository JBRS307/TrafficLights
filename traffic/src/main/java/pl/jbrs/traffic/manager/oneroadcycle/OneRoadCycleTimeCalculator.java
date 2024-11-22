package pl.jbrs.traffic.manager.oneroadcycle;

import pl.jbrs.traffic.model.TrafficLight;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.configuration.ModelConfiguration;

import java.util.List;
import java.util.Map;

public class OneRoadCycleTimeCalculator {
    private final ModelConfiguration config;

    public OneRoadCycleTimeCalculator(ModelConfiguration config) {
        this.config = config;
    }

    // Works by this formula
    // nextStateTime = Math.round((CW / CO) * config.stateTime)
    //    - CW - cars waiting at the road that will get green light + priority * priorityMultiplier
    //    - CO - mean of CarsWaiting at other roads with their priorities with multiplier added
    // If result is lower than minimal state time, then returned time is minimal state time
    public int calcNextStepLength(Map<RoadDirection, Road> roadMap, OneRoadCycleState currentState) {
        if (!currentState.isYellow()) {
            return config.getYellowTime();
        }

        int stateCars = 0;
        int restCars = 0;

        RoadDirection currentDirection = currentState.next().toRoadDirection();

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
        // Should not be possible because priority cannot be lower than 1,
        // but it's better to keep it independently safe
        if (restCars == 0) {
            restCars = 1;
        }
        return Math.max(Math.round(((float)stateCars / restCars) * config.getStateLength()),
                        config.getMinStateLength());
    }
}
