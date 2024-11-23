package pl.jbrs.traffic.creator;

import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.configuration.ModelConfiguration;
import pl.jbrs.traffic.configuration.RoadConfiguration;
import pl.jbrs.traffic.configuration.SimulationConfiguration;
import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.simulation.BasicSimulation;
import pl.jbrs.traffic.simulation.Simulation;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BasicSimulationCreatorTest {
    @Test
    public void createSimulationTest() {
        // given
        Map<RoadDirection, RoadConfiguration> roadConfig = Map.ofEntries(
                Map.entry(RoadDirection.NORTH, new RoadConfiguration()),
                Map.entry(RoadDirection.EAST, new RoadConfiguration()),
                Map.entry(RoadDirection.SOUTH, new RoadConfiguration()),
                Map.entry(RoadDirection.WEST, new RoadConfiguration())
        );
        BasicSimulationCreator simulationCreator = new BasicSimulationCreator(roadConfig, new SimulationConfiguration(), new ModelConfiguration());

        // when
        Simulation simulation = simulationCreator.createSimulation();

        // then
        assertInstanceOf(BasicSimulation.class, simulation);
    }
}