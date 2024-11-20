package pl.jbrs.traffic.manager.oneroadcycle;

import pl.jbrs.traffic.manager.AbstractTrafficManager;
import pl.jbrs.traffic.model.LightColor;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.simulation.configuration.ModelConfiguration;

import java.util.Collection;
import java.util.Map;

public class OneRoadCycleManager extends AbstractTrafficManager {
    private OneRoadCycleState currentState = OneRoadCycleState.NORTH;
    private final OneRoadCycleTimeCalculator timeCalculator;

    public OneRoadCycleManager(Map<RoadDirection, Road> roadMap, ModelConfiguration config) {
        super(roadMap, config);
        this.timeCalculator = new OneRoadCycleTimeCalculator(config);
    }

    // Change colors of current road to Yellow
    // If there is pedestrian light on the road to the right
    // switch it to red
    private void toYellow(OneRoadCycleState state) {
        RoadDirection currDirection = state.toRoadDirection();

        if (roadMap.get(currDirection.prev()).hasCrosswalk()) {
            roadMap.get(currDirection.prev()).getPedestrianLight().setColor(LightColor.RED);
        }

        roadMap.get(currDirection)
                .getLights()
                .values()
                .stream()
                .flatMap(Collection::stream)
                .forEach(light -> light.setColor(LightColor.YELLOW));
    }

    private void toRed(OneRoadCycleState state) {
        roadMap.get(state.toRoadDirection())
                .getLights()
                .values()
                .stream()
                .flatMap(Collection::stream)
                .forEach(light -> light.setColor(LightColor.RED));
    }

    private void toGreen(OneRoadCycleState state) {
        RoadDirection currDirection = state.toRoadDirection();
        if (roadMap.get(currDirection.prev()).hasCrosswalk()) {
            roadMap.get(currDirection.prev()).getPedestrianLight().setColor(LightColor.GREEN);
        }

        roadMap.get(currDirection)
                .getLights()
                .values()
                .stream()
                .flatMap(Collection::stream)
                .forEach(light -> light.setColor(LightColor.GREEN));
    }

    @Override
    public int nextState() {
        int stateTime = timeCalculator.calcNextStepLength(roadMap, currentState);
        if (currentState.isYellow()) {
            toRed(currentState);
            toGreen(currentState.next());
        } else {
            toYellow(currentState);
        }
        currentState = currentState.next();
        return stateTime;
    }
}
