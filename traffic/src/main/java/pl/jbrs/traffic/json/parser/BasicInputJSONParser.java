package pl.jbrs.traffic.json.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.jbrs.traffic.json.parser.inputoptions.AddPedestrianOption;
import pl.jbrs.traffic.json.parser.inputoptions.AddVehicleOption;
import pl.jbrs.traffic.json.parser.inputoptions.CommandOption;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.simulation.command.AddCarCommand;
import pl.jbrs.traffic.simulation.command.AddPedestrianCommand;
import pl.jbrs.traffic.simulation.command.Command;
import pl.jbrs.traffic.simulation.command.StepCommand;

import java.util.LinkedList;
import java.util.Queue;

public class BasicInputJSONParser implements InputJSONParser {
    private final Queue<Command> commandQueue = new LinkedList<>();
    private final JSONArray inputJSON;

    public BasicInputJSONParser(String jsonString) throws JSONException {
        this.inputJSON = new JSONObject(jsonString).getJSONArray("commands");
    }

    private Command parseAddVehicle(JSONObject commandJSON) throws JSONException, IllegalArgumentException {
        RoadDirection startRoad = RoadDirection.fromString(commandJSON.getString(AddVehicleOption.StartRoad.toString()));
        RoadDirection endRoad = RoadDirection.fromString(commandJSON.getString(AddVehicleOption.EndRoad.toString()));
        String vehicleId = commandJSON.getString(AddVehicleOption.VehicleID.toString());

        return new AddCarCommand(startRoad, endRoad, vehicleId);
    }

    private Command parseAddPedestrian(JSONObject commandJSON) throws JSONException, IllegalArgumentException {
        RoadDirection road = RoadDirection.fromString(commandJSON.getString(AddPedestrianOption.Road.toString()));
        return new AddPedestrianCommand(road);
    }

    private Command parseStep() {
        return new StepCommand();
    }


    @Override
    public Queue<Command> parseCommands() {
        for (int i = 0; i < inputJSON.length(); i++) {
            JSONObject commandJSON;
            try {
                commandJSON = inputJSON.getJSONObject(i);
            } catch (JSONException e) {
                System.err.println("Error parsing command: " + e.getMessage());
                continue;
            }

            try {
                String commandType = commandJSON.getString("type");
                commandQueue.add(switch (CommandOption.fromString(commandType)) {
                    case CommandOption.AddPedestrian -> parseAddPedestrian(commandJSON);
                    case CommandOption.AddVehicle -> parseAddVehicle(commandJSON);
                    case CommandOption.Step -> parseStep();
                });
            } catch (JSONException ignored) {
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                System.out.println("Command omitted!");
            }
        }
        return commandQueue;
    }
}
