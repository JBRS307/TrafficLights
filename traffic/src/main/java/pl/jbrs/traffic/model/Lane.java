package pl.jbrs.traffic.model;

import pl.jbrs.traffic.exception.VehicleExistsException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Lane {
    private final Queue<Vehicle> vehicles = new LinkedList<>();
    private final LaneDirection direction;

    public Lane(LaneDirection direction) {
        this.direction = direction;
    }

    public LaneDirection getDirection() {
        return direction;
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void moveVehicle() {
        Vehicle moved = vehicles.poll();
    }

    public Vehicle checkFirstVehicle() {
        return vehicles.peek();
    }

    public int getWaitingCars() {
        return vehicles.size();
    }
}
