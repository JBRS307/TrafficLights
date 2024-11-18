package pl.jbrs.traffic.model;

import java.util.LinkedList;
import java.util.Queue;

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

    public void moveCar() {
        vehicles.poll();
    }

    public int getWaitingCars() {
        return vehicles.size();
    }
}
