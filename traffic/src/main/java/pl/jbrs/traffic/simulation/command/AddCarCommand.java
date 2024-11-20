package pl.jbrs.traffic.simulation.command;

import pl.jbrs.traffic.model.Vehicle;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.simulation.Simulation;

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
    public void execute(Simulation simulation) {
        simulation.addVehicle(new Vehicle(vehicleId, startDirection, endDirection));
    }
}
