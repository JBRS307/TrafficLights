package pl.jbrs.traffic.manager;

import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.simulation.configuration.ModelConfiguration;

import java.util.Map;

public abstract class AbstractTrafficManager implements TrafficManager {
    protected final Map<RoadDirection, Road> roadMap;
    protected final ModelConfiguration config;

    public AbstractTrafficManager(Map<RoadDirection, Road> roadMap, ModelConfiguration config) {
        this.roadMap = roadMap;
        this.config = config;
    }
}
