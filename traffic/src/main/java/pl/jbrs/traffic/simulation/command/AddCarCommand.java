package pl.jbrs.traffic.simulation.command;

import pl.jbrs.traffic.manager.TrafficManager;
import pl.jbrs.traffic.model.MoveDirection;
import pl.jbrs.traffic.model.Vehicle;
import pl.jbrs.traffic.model.road.RoadDirection;

public class AddCarCommand implements Command {
    private RoadDirection startDirection;
    private RoadDirection endDirection;
    private String vehicleId;

    public AddCarCommand(RoadDirection startDirection, RoadDirection endDirection, String vehicleId) {
        this.startDirection = startDirection;
        this.endDirection = endDirection;
        this.vehicleId = vehicleId;
    }

    @Override
    public void execute(TrafficManager trafficManager) {
        trafficManager.addVehicle(new Vehicle(vehicleId, startDirection, endDirection));
    }
}
