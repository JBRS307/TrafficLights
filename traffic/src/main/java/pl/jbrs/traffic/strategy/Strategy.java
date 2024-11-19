package pl.jbrs.traffic.strategy;

import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.model.road.Road;

import java.util.Map;

public interface Strategy {
    void calcNextStep(Map<RoadDirection, Road> roadMap);
}
