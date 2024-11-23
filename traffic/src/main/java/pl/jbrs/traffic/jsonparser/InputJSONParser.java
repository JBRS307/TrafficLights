package pl.jbrs.traffic.jsonparser;

import pl.jbrs.traffic.simulation.command.Command;

import java.util.Queue;

public interface InputJSONParser {
    Queue<Command> parseCommands();
}
