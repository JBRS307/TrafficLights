package pl.jbrs.traffic.json.parser;

import pl.jbrs.traffic.simulation.command.Command;

import java.util.Queue;

public interface InputJSONParser {
    Queue<Command> parseCommands();
}
