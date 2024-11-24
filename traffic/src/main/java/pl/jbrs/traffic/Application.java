package pl.jbrs.traffic;

import org.json.JSONObject;
import pl.jbrs.traffic.configuration.ModelConfiguration;
import pl.jbrs.traffic.configuration.RoadConfiguration;
import pl.jbrs.traffic.configuration.SimulationConfiguration;
import pl.jbrs.traffic.creator.BasicSimulationCreator;
import pl.jbrs.traffic.creator.SimulationCreator;
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
    private static Application app;
    public static Application getInstance() throws IOException, MissingInputFileException {
        if (app == null) {
            app = new Application();
        }
        return app;
    }

    private final JsonIO jsonIo = new JsonIO();
    private final Simulation simulation;
    private final Queue<Command> commandQueue;

    private Application() throws IOException {
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
            command.execute(simulation);
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
