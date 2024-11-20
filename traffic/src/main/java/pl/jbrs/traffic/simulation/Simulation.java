package pl.jbrs.traffic.simulation;

import pl.jbrs.traffic.model.Vehicle;
import pl.jbrs.traffic.model.road.RoadDirection;

public interface Simulation {
    void addPedestrian(RoadDirection roadDirection);
    void addVehicle(Vehicle vehicle);
    void step();
}
