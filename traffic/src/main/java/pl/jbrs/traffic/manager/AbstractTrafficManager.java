package pl.jbrs.traffic.manager;

import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.simulation.Configuration;

import java.util.Map;

public abstract class AbstractTrafficManager implements TrafficManager {
    protected final Map<RoadDirection, Road> roadMap;
    protected final Configuration config;

    public AbstractTrafficManager(Map<RoadDirection, Road> roadMap, Configuration config) {
        this.roadMap = roadMap;
        this.config = config;
    }
}
