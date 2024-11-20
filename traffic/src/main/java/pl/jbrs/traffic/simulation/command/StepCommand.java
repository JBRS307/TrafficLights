package pl.jbrs.traffic.simulation.command;

import pl.jbrs.traffic.simulation.Simulation;

public class StepCommand implements Command {
    @Override
    public void execute(Simulation simulation) {
        simulation.step();
    }
}
