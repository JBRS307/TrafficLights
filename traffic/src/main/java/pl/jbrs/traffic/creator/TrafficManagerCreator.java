package pl.jbrs.traffic.creator;

import pl.jbrs.traffic.configuration.ModelConfiguration;
import pl.jbrs.traffic.configuration.SimulationConfiguration;
import pl.jbrs.traffic.configuration.TrafficStrategy;
import pl.jbrs.traffic.manager.TrafficManager;
import pl.jbrs.traffic.manager.oneroadcycle.OneRoadCycleManager;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.Map;

public class TrafficManagerCreator {
    private final SimulationConfiguration simulationConfiguration;
    private final ModelConfiguration modelConfiguration;
    private final Map<RoadDirection, Road> roadMap;

    public TrafficManagerCreator(SimulationConfiguration simulationConfiguration, ModelConfiguration modelConfiguration, Map<RoadDirection, Road> roadMap) {
        this.simulationConfiguration = simulationConfiguration;
        this.modelConfiguration = modelConfiguration;
        this.roadMap = roadMap;
    }

    private TrafficManager createOneRoadCycleManager() {
        return new OneRoadCycleManager(roadMap, modelConfiguration);
    }

    // Prepared for expanding
    public TrafficManager createTrafficManager() {
        return switch (simulationConfiguration.getTrafficStrategy()) {
            case TrafficStrategy.ONE_ROAD_CYCLE -> createOneRoadCycleManager();
        };
    }
}
