package pl.jbrs.traffic.manager;

import pl.jbrs.traffic.model.Vehicle;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.strategy.Strategy;

import java.util.Map;

public abstract class AbstractTrafficManager {
    protected final Map<RoadDirection, Road> roadMap;

    protected final Strategy strategy;

    public AbstractTrafficManager(Map<RoadDirection, Road> roadMap, Strategy strategy) {
        this.roadMap = roadMap;
        this.strategy = strategy;
    }

    public void addVehicle(Vehicle v) {
        roadMap.get(v.startRoad()).addVehicle(v);
    }
}
