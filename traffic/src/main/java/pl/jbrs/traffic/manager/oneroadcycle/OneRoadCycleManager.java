package pl.jbrs.traffic.manager.oneroadcycle;

import pl.jbrs.traffic.manager.AbstractTrafficManager;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.simulation.Configuration;

import java.util.Map;

public class OneRoadCycleManager extends AbstractTrafficManager {
    private OneRoadCycleState currentState = OneRoadCycleState.NORTH;
    private final OneRoadCycleTimeCalculator timeCalculator;

    public OneRoadCycleManager(Map<RoadDirection, Road> roadMap, Configuration config) {
        super(roadMap, config);
        this.timeCalculator = new OneRoadCycleTimeCalculator(config);
    }

    @Override
    public void nextState() {
        int stateTime = timeCalculator.calcNextStepLength(roadMap, currentState);

    }
}
