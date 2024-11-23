package pl.jbrs.traffic.jsonparser;

import org.json.JSONObject;
import pl.jbrs.traffic.simulation.command.Command;

import java.util.LinkedList;
import java.util.Queue;

public class BasicInputJSONParser implements InputJSONParser {
    private final Queue<Command> commandQueue = new LinkedList<>();
    private final JSONObject inputJSON;

    public BasicInputJSONParser(String jsonString) {
        this.inputJSON = new JSONObject(jsonString);
    }

    @Override
    public Queue<Command> parseCommands() {
        return null;
    }
}
