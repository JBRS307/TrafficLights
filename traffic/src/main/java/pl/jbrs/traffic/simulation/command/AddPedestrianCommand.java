package pl.jbrs.traffic.simulation.command;

import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.simulation.Simulation;

public class AddPedestrianCommand implements Command {
    private RoadDirection roadDirection;

    public AddPedestrianCommand(RoadDirection roadDirection) {
        this.roadDirection = roadDirection;
    }

    @Override
    public void execute(Simulation simulation) {
        simulation.addPedestrian(roadDirection);
    }
}
