package pl.jbrs.traffic.simulation;

import org.json.JSONObject;
import pl.jbrs.traffic.model.Vehicle;
import pl.jbrs.traffic.model.road.RoadDirection;

public interface Simulation {
    void addPedestrian(RoadDirection roadDirection);
    void addVehicle(Vehicle vehicle);
    void step();
    JSONObject getSimulationResultJSON();
}
