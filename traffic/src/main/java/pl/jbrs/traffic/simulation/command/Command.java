package pl.jbrs.traffic.simulation.command;

import pl.jbrs.traffic.exception.CommandException;
import pl.jbrs.traffic.simulation.Simulation;

@FunctionalInterface
public interface Command {
    void execute(Simulation simulation) throws CommandException;
}
