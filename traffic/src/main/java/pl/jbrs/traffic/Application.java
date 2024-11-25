package pl.jbrs.traffic;

import org.json.JSONObject;
import pl.jbrs.traffic.configuration.ModelConfiguration;
import pl.jbrs.traffic.configuration.RoadConfiguration;
import pl.jbrs.traffic.configuration.SimulationConfiguration;
import pl.jbrs.traffic.creator.BasicSimulationCreator;
import pl.jbrs.traffic.creator.SimulationCreator;
import pl.jbrs.traffic.exception.CommandException;
import pl.jbrs.traffic.exception.MissingInputFileException;
import pl.jbrs.traffic.json.JsonIO;
import pl.jbrs.traffic.json.parser.BasicConfigJSONParser;
import pl.jbrs.traffic.json.parser.BasicInputJSONParser;
import pl.jbrs.traffic.json.parser.ConfigJSONParser;
import pl.jbrs.traffic.json.parser.InputJSONParser;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.simulation.Simulation;
import pl.jbrs.traffic.simulation.command.Command;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Map;
import java.util.Queue;

public class Application {
    public static Application fromArgs(String[] args) throws IOException {
        return switch (args.length) {
            case 0 -> new Application(DefaultPaths.INPUT, DefaultPaths.OUTPUT, DefaultPaths.CONFIG);
            case 1 -> new Application(args[0], DefaultPaths.OUTPUT, DefaultPaths.CONFIG);
            case 2 -> new Application(args[0], args[1], DefaultPaths.CONFIG);
            default -> new Application(args[0], args[1], args[2]);
        };
    }

    private final JsonIO jsonIo;
    private final Simulation simulation;
    private final Queue<Command> commandQueue;

    private Application(String inputPath, String outputPath, String configPath) throws IOException {
        jsonIo = new JsonIO(inputPath, outputPath, configPath);
        ModelConfiguration modelConfiguration;
        Map<RoadDirection, RoadConfiguration> roadConfiguration;
        SimulationConfiguration simulationConfiguration;
        try {
            String configJSONString = jsonIo.readConfig();
            ConfigJSONParser configParser = new BasicConfigJSONParser(configJSONString);

            JSONObject modelConfigJSON = configParser.modelConfigurationJSON();
            JSONObject simulationConfigJSON = configParser.simulationConfigurationJSON();
            JSONObject roadConfigJSON = configParser.roadConfigurationJSON();

            modelConfiguration = ModelConfiguration.fromJSON(modelConfigJSON);
            roadConfiguration = RoadConfiguration.fromJSON(roadConfigJSON);
            simulationConfiguration = SimulationConfiguration.fromJSON(simulationConfigJSON);
        } catch (NoSuchFileException e) {
            System.out.println("No config file, using default configuration...");
            modelConfiguration = new ModelConfiguration();
            simulationConfiguration = new SimulationConfiguration();
            roadConfiguration = Map.ofEntries(
                    Map.entry(RoadDirection.NORTH, new RoadConfiguration()),
                    Map.entry(RoadDirection.EAST, new RoadConfiguration()),
                    Map.entry(RoadDirection.SOUTH, new RoadConfiguration()),
                    Map.entry(RoadDirection.WEST, new RoadConfiguration())
            );
        } catch (IOException e) {
            System.err.println("Error reading config file: " + e.getMessage());
            modelConfiguration = new ModelConfiguration();
            simulationConfiguration = new SimulationConfiguration();
            roadConfiguration = Map.ofEntries(
                    Map.entry(RoadDirection.NORTH, new RoadConfiguration()),
                    Map.entry(RoadDirection.EAST, new RoadConfiguration()),
                    Map.entry(RoadDirection.SOUTH, new RoadConfiguration()),
                    Map.entry(RoadDirection.WEST, new RoadConfiguration())
            );
        }
        SimulationCreator simulationCreator = new BasicSimulationCreator(roadConfiguration, simulationConfiguration, modelConfiguration);
        simulation = simulationCreator.createSimulation();

        try {
            String inputJSONString = jsonIo.readInput();

            InputJSONParser inputParser = new BasicInputJSONParser(inputJSONString);
            commandQueue = inputParser.parseCommands();
        } catch (NoSuchFileException e) {
            throw new MissingInputFileException("Input file is missing!");
        }
    }

    public void runSimulation() {
        for (Command command : commandQueue) {
            try {
                command.execute(simulation);
            } catch (CommandException e) {
                System.err.println("Illegal command!");
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveResultToJSON() {
        try {
            jsonIo.writeOutput(simulation.getSimulationResultJSON());
        } catch (IOException e) {
            System.err.println("Error writing results to JSON: " + e.getMessage());
        }
    }

}
