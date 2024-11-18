package pl.jbrs.traffic.simulation.command;

import pl.jbrs.traffic.manager.TrafficManager;

public interface Command {
    void execute(TrafficManager trafficManager);
}
