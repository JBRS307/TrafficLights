package pl.jbrs.traffic.strategy;

import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.simulation.Configuration;

import java.util.Map;

// Most basic strategy possible
// Every road is allowed to go in 4 step cycle
// If there is crosswalk on any road then pedestrians are allowed to cross
// whenever the lane on their left has green light (situation when drivers turning right
// and pedestrians have a collision is common on real life crosswalks)
public class OneRoadCycleStrategy implements Strategy {
    private final Configuration config;

    public OneRoadCycleStrategy(Configuration config) {
        this.config = config;
    }

    @Override
    public void calcNextState(Map<RoadDirection, Road> roadMap) {
        
    }
}
