package pl.jbrs.traffic.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.model.road.PedestrianRoad;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {
    @Test
    public void canDriveTest() {
        // given
        Map<RoadDirection, Road> greenRoads = new HashMap<>();
        PedestrianRoad pedestrianRoad = new PedestrianRoad(RoadDirection.EAST, Map.of(), new PedestrianLight(), 0);
        pedestrianRoad.getPedestrianLight().setColor(LightColor.GREEN);
        greenRoads.put(RoadDirection.EAST, pedestrianRoad);

        Vehicle vehicle = new Vehicle("v1", RoadDirection.SOUTH, RoadDirection.EAST);

        // when, then
        assertTrue(vehicle.canDrive(greenRoads));

        // when
        pedestrianRoad.addPedestrian();
        pedestrianRoad.movePedestrians();
        // then
        assertFalse(vehicle.canDrive(greenRoads));

        // given
        vehicle = new Vehicle("v2", RoadDirection.SOUTH, RoadDirection.NORTH);
        // when, then
        assertTrue(vehicle.canDrive(greenRoads));

    }

}