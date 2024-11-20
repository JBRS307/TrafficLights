package pl.jbrs.traffic.creator;

import pl.jbrs.traffic.configuration.ModelConfiguration;
import pl.jbrs.traffic.configuration.RoadConfiguration;
import pl.jbrs.traffic.configuration.SimulationConfiguration;
import pl.jbrs.traffic.manager.TrafficManager;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.simulation.BasicSimulation;
import pl.jbrs.traffic.simulation.Simulation;

import java.util.HashMap;
import java.util.Map;

public class BasicSimulationCreator implements SimulationCreator {
    private final Map<RoadDirection, RoadConfiguration> roadConfig;
    private final SimulationConfiguration simulationConfig;
    private final ModelConfiguration modelConfig;

    public BasicSimulationCreator(Map<RoadDirection, RoadConfiguration> roadConfig, SimulationConfiguration simulationConfig, ModelConfiguration modelConfig) {
        this.roadConfig = roadConfig;
        this.simulationConfig = simulationConfig;
        this.modelConfig = modelConfig;
    }

    private Map<RoadDirection, Road> createRoads() {
        Map<RoadDirection, Road> createdRoads = new HashMap<>();
        for (RoadDirection direction : roadConfig.keySet()) {
            createdRoads.put(direction, new RoadCreator(roadConfig.get(direction), direction).createRoad());
        }
        return createdRoads;
    }

    private TrafficManager createTrafficManager(Map<RoadDirection, Road> createdRoads) {
        return new TrafficManagerCreator(simulationConfig, modelConfig, createdRoads).createTrafficManager();
    }


    @Override
    public Simulation createSimulation() {
        Map<RoadDirection, Road> roads = createRoads();
        TrafficManager trafficManager = createTrafficManager(roads);

        return new BasicSimulation(roads, trafficManager);
    }
}
