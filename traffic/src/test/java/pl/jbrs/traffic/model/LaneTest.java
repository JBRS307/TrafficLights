package pl.jbrs.traffic.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LaneTest {
    private static List<Vehicle> vehicles;
    @BeforeAll
    public static void initVehicles() {
        vehicles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            vehicles.add(new Vehicle("v" + i, RoadDirection.SOUTH, RoadDirection.NORTH));
        }
    }

    @Test
    public void addVehicleMoveVehicleTest() {
        // given
        Lane lane = new Lane(LaneDirection.STRAIGHT);

        // when
        vehicles.forEach(lane::addVehicle);
        // then
        assertEquals(vehicles.getFirst(), lane.checkFirstVehicle());

        // when
        lane.moveVehicle();
        // then
        assertEquals(vehicles.get(1), lane.checkFirstVehicle());
    }

}