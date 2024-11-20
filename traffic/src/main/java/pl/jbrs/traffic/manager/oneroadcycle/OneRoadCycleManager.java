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

    private void toYellow(OneRoadCycleState state) {

    }

    private void toRed(OneRoadCycleState state) {

    }

    private void toGreen(OneRoadCycleState state) {

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
