package pl.jbrs.traffic.simulation;

import org.json.JSONObject;
import pl.jbrs.traffic.manager.TrafficManager;
import pl.jbrs.traffic.model.Lane;
import pl.jbrs.traffic.model.LightColor;
import pl.jbrs.traffic.model.TrafficLight;
import pl.jbrs.traffic.model.Vehicle;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.simulation.result.BasicSimulationResult;
import pl.jbrs.traffic.simulation.result.SimulationResult;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BasicSimulation implements Simulation {
    private final Map<RoadDirection, Road> roadMap;
    private final TrafficManager trafficManager;
    private final SimulationResult simulationResult;

    private int stateStepsLeft = 0;

    public BasicSimulation(Map<RoadDirection, Road> roadMap, TrafficManager trafficManager, SimulationResult simulationResult) {
        this.roadMap = roadMap;
        this.trafficManager = trafficManager;
        this.simulationResult = simulationResult;
    }

    public BasicSimulation(Map<RoadDirection, Road> roadMap, TrafficManager trafficManager) {
        this(roadMap, trafficManager, new BasicSimulationResult());
    }

    @Override
    public JSONObject getSimulationResultJSON() {
        return simulationResult.getJSONResult();
    }

    public Map<RoadDirection, Road> getRoadMap() {
        return roadMap;
    }

    public TrafficManager getTrafficManager() {
        return trafficManager;
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
                .filter(road -> (road.hasCrosswalk() && road.getPedestrianLight().getColor() == LightColor.GREEN))
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
                simulationResult.addVehicleToCurrentStep(vehicle);
            }
        });
        simulationResult.saveStep();
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
