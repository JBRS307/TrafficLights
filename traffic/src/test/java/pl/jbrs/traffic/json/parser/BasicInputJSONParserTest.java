package pl.jbrs.traffic.json.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.simulation.command.AddCarCommand;
import pl.jbrs.traffic.simulation.command.AddPedestrianCommand;
import pl.jbrs.traffic.simulation.command.Command;
import pl.jbrs.traffic.simulation.command.StepCommand;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class BasicInputJSONParserTest {
    // given
    private static String inputStringCorrect;
    private static String inputStringIncorrect;
    @BeforeAll
    public static void setUp() {
        inputStringCorrect = "{" +
                            "\"commands\":[" +
                                "{" +
                                    "\"type\":\"addVehicle\"," +
                                    "\"vehicleId\":\"v1\"," +
                                    "\"startRoad\":\"north\"," +
                                    "\"endRoad\":\"south\"," +
                                "}," +
                                "{" +
                                    "\"type\":\"addVehicle\"," +
                                    "\"vehicleId\":\"v2\"," +
                                    "\"startRoad\":\"east\"," +
                                    "\"endRoad\":\"west\"," +
                                "}," +
                                "{" +
                                    "\"type\":\"step\"," +
                                "}," +
                                "{" +
                                    "\"type\":\"step\"," +
                                "}," +
                                "{" +
                                    "\"type\":\"addPedestrian\"," +
                                    "\"road\":\"east\"," +
                                "}," +
                                "{" +
                                    "\"type\":\"addPedestrian\"," +
                                    "\"road\":\"west\"," +
                                "}" +
                            "]" +
                        "}";

        inputStringIncorrect =
                "{" +
                    "\"commands\":[" +
                        "{" +
                            "\"type\":\"addVehicle\"," +
                            "\"vehicleId\":\"v1\"," +
                            "\"startRoad\":\"north\"," +
                            "\"endRoad\":\"south\"," +
                        "}," +
                        "{" +
                            "\"sdfdsf\":\"dfsdf\"," +
                        "}," +
                        "{" +
                            "\"type\":\"addPedestrian\"," +
                            "\"roooad\":\"west\"," +
                        "}," +
                        "{" +
                            "\"type\":\"step\"," +
                        "}" +
                    "]" +
                "}";
    }

    @Test
    public void parseCommandsCorrectTest() {
        // given
        BasicInputJSONParser parser = new BasicInputJSONParser(inputStringCorrect);

        // when
        Queue<Command> commandQueue = parser.parseCommands();

        // then
        Command command = commandQueue.poll();
        assertInstanceOf(AddCarCommand.class, command);

        command = commandQueue.poll();
        assertInstanceOf(AddCarCommand.class, command);

        command = commandQueue.poll();
        assertInstanceOf(StepCommand.class, command);

        command = commandQueue.poll();
        assertInstanceOf(StepCommand.class, command);

        command = commandQueue.poll();
        assertInstanceOf(AddPedestrianCommand.class, command);

        command = commandQueue.poll();
        assertInstanceOf(AddPedestrianCommand.class, command);

        command = commandQueue.poll();
        assertNull(command);
    }

    @Test
    public void parseCommandsIncorrectTest() {
        // given
        BasicInputJSONParser parser = new BasicInputJSONParser(inputStringIncorrect);

        // when
        Queue<Command> commandQueue = parser.parseCommands();

        // then
        Command command = commandQueue.poll();
        assertInstanceOf(AddCarCommand.class, command);

        command = commandQueue.poll();
        assertInstanceOf(StepCommand.class, command);

        command = commandQueue.poll();
        assertNull(command);
    }

}