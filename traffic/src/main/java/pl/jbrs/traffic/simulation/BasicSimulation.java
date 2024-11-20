package pl.jbrs.traffic.simulation;

import pl.jbrs.traffic.manager.TrafficManager;
import pl.jbrs.traffic.model.Vehicle;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.Map;

public class BasicSimulation implements Simulation {
    protected final Map<RoadDirection, Road> roadMap;
    protected final TrafficManager trafficManager;

    public BasicSimulation(Map<RoadDirection, Road> roadMap, TrafficManager trafficManager) {
        this.roadMap = roadMap;
        this.trafficManager = trafficManager;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        roadMap.get(vehicle.startRoad()).addVehicle(vehicle);
    }
}
