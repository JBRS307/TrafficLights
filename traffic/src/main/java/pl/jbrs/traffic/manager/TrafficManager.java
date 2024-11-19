package pl.jbrs.traffic.manager;

import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.strategy.Strategy;

import java.util.Map;

public class TrafficManager {
    private final Map<RoadDirection, Road> roadMap;

    private final Strategy strategy;

    public TrafficManager(Map<RoadDirection, Road> roadMap, Strategy strategy) {
        this.roadMap = roadMap;
        this.strategy = strategy;
    }
}
