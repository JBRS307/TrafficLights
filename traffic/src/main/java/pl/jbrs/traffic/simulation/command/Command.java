package pl.jbrs.traffic.simulation.command;

import pl.jbrs.traffic.manager.TrafficManager;

@FunctionalInterface
public interface Command {
    void execute(TrafficManager trafficManager);
}
