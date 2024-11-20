package pl.jbrs.traffic.simulation;

import pl.jbrs.traffic.manager.TrafficManager;
import pl.jbrs.traffic.model.Lane;
import pl.jbrs.traffic.model.LightColor;
import pl.jbrs.traffic.model.TrafficLight;
import pl.jbrs.traffic.model.Vehicle;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BasicSimulation implements Simulation {
    private final Map<RoadDirection, Road> roadMap;
    private final TrafficManager trafficManager;

    private int stateStepsLeft;

    public BasicSimulation(Map<RoadDirection, Road> roadMap, TrafficManager trafficManager) {
        this.roadMap = roadMap;
        this.trafficManager = trafficManager;
        stateStepsLeft = 0;
    }

    private List<Lane> getGreenLanes() {
        return roadMap.values()
                .stream()
                .flatMap(road -> road.getLights().values().stream())
                .flatMap(List::stream)
                .filter(light -> light.getColor() == LightColor.GREEN)
                .map(TrafficLight::getLane)
                .toList();
    }

    private Map<RoadDirection, Road> getGreenPedestrianRoads() {
        return roadMap.values()
                .stream()
                .filter(road -> road.getPedestrianLight().getColor() == LightColor.GREEN)
                .collect(Collectors.toMap(Road::getDirection, Function.identity()));
    }

    @Override
    public void step() {
        if (stateStepsLeft == 0) {
            stateStepsLeft = trafficManager.nextState();
        }

        List<Lane> greenLanes = getGreenLanes();
        Map<RoadDirection, Road> greenPedestrianRoads = getGreenPedestrianRoads();
        greenPedestrianRoads.values().forEach(Road::movePedestrians);

        greenLanes.forEach(lane -> {
            Vehicle vehicle = lane.checkFirstVehicle();
            if (vehicle != null && vehicle.canDrive(greenPedestrianRoads)) {
                lane.moveVehicle();
                // TODO write vehicle to json
            }
        });
        stateStepsLeft--;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        roadMap.get(vehicle.startRoad()).addVehicle(vehicle);
    }

    @Override
    public void addPedestrian(RoadDirection roadDirection) {
        roadMap.get(roadDirection).addPedestrian();
    }
}
