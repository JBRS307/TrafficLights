package pl.jbrs.traffic.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.configuration.ModelConfiguration;
import pl.jbrs.traffic.configuration.RoadConfiguration;
import pl.jbrs.traffic.configuration.SimulationConfiguration;
import pl.jbrs.traffic.creator.BasicSimulationCreator;
import pl.jbrs.traffic.creator.SimulationCreator;
import pl.jbrs.traffic.exception.ImpossibleMoveException;
import pl.jbrs.traffic.exception.NoCrosswalkException;
import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.Vehicle;
import pl.jbrs.traffic.model.road.PedestrianRoad;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BasicSimulationTest {
    // given
    private BasicSimulation basicSimulation;
    @BeforeEach
    public void prepareSimulation() {
        RoadConfiguration southRoadConfig = new RoadConfiguration();
        southRoadConfig.setCrosswalk(true);
        southRoadConfig.putToLanes(LaneDirection.UTURN_LEFT, 0);
        southRoadConfig.putToLanes(LaneDirection.LEFT, 1);
        Map<RoadDirection, RoadConfiguration> roadConfig = Map.ofEntries(
                Map.entry(RoadDirection.NORTH, new RoadConfiguration()),
                Map.entry(RoadDirection.EAST, new RoadConfiguration()),
                Map.entry(RoadDirection.SOUTH, southRoadConfig),
                Map.entry(RoadDirection.WEST, new RoadConfiguration())
        );
        SimulationCreator creator = new BasicSimulationCreator(roadConfig, new SimulationConfiguration(), new ModelConfiguration());
        basicSimulation = (BasicSimulation) creator.createSimulation();
    }

    @Test
    public void addVehicleTest() {
        // given
        Vehicle v1 = new Vehicle("v1", RoadDirection.SOUTH, RoadDirection.WEST);
        Vehicle v2 = new Vehicle("v2", RoadDirection.NORTH, RoadDirection.SOUTH);

        // when
        basicSimulation.addVehicle(v1);
        basicSimulation.addVehicle(v2);

        // then
        Vehicle added1 = basicSimulation.getRoadMap()
                .get(RoadDirection.SOUTH)
                .getLights()
                .get(LaneDirection.LEFT)
                .getFirst()
                .getLane().checkFirstVehicle();
        Vehicle added2 = basicSimulation.getRoadMap()
                .get(RoadDirection.NORTH)
                .getLights()
                .get(LaneDirection.STRAIGHT)
                .getFirst()
                .getLane().checkFirstVehicle();

        assertEquals(v1, added1);
        assertEquals(v2, added2);

        // given
        Vehicle v3 = new Vehicle("v3", RoadDirection.SOUTH, RoadDirection.SOUTH);
        // when, then
        assertThrows(ImpossibleMoveException.class, () -> basicSimulation.addVehicle(v3));

    }

    @Test
    public void addPedestrianTest() {
        // given
        PedestrianRoad southRoad = (PedestrianRoad) basicSimulation.getRoadMap()
                .get(RoadDirection.SOUTH);

        // when
        basicSimulation.addPedestrian(RoadDirection.SOUTH);
        basicSimulation.addPedestrian(RoadDirection.SOUTH);

        // then
        int expectedPedestrians = 2;
        int actualPedestrians = southRoad.getPedestriansWaiting();
        assertEquals(expectedPedestrians, actualPedestrians);

        // when, then
        assertThrowsExactly(NoCrosswalkException.class, () -> basicSimulation.addPedestrian(RoadDirection.NORTH));
    }
}