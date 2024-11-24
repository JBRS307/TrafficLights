package pl.jbrs.traffic.model;

import pl.jbrs.traffic.exception.VehicleExistsException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Lane {
    private final Queue<Vehicle> vehicles = new LinkedList<>();
    private final Set<String> idSet = new HashSet<>();
    private final LaneDirection direction;

    public Lane(LaneDirection direction) {
        this.direction = direction;
    }

    public LaneDirection getDirection() {
        return direction;
    }

    public void addVehicle(Vehicle v) throws VehicleExistsException {
        if (idSet.contains(v.id())) {
            throw new VehicleExistsException("Vehicle with id " + v.id() + " already exists");
        }
        vehicles.add(v);
        idSet.add(v.id());
    }

    public void moveVehicle() {
        Vehicle moved = vehicles.poll();
        if (moved != null) {
            idSet.remove(moved.id());
        }
    }

    public Vehicle checkFirstVehicle() {
        return vehicles.peek();
    }

    public int getWaitingCars() {
        return vehicles.size();
    }
}
