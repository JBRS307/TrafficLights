package pl.jbrs.traffic.model;

import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.model.road.RoadDirection;

import static org.junit.jupiter.api.Assertions.*;

public class MoveDirectionTest {

    @Test
    void calcDirectionTest() {
        RoadDirection start, end;

        start = RoadDirection.NORTH;
        end = RoadDirection.SOUTH;
        assertEquals(MoveDirection.STRAIGHT, MoveDirection.calcDirection(start, end));

        start = RoadDirection.EAST;
        end = RoadDirection.NORTH;
        assertEquals(MoveDirection.RIGHT, MoveDirection.calcDirection(start, end));

        start = RoadDirection.NORTH;
        end = RoadDirection.WEST;
        assertEquals(MoveDirection.RIGHT, MoveDirection.calcDirection(start, end));

        start = RoadDirection.WEST;
        end = RoadDirection.WEST;
        assertEquals(MoveDirection.UTURN, MoveDirection.calcDirection(start, end));

        start = RoadDirection.WEST;
        end = RoadDirection.NORTH;
        assertEquals(MoveDirection.LEFT, MoveDirection.calcDirection(start, end));

        start = RoadDirection.SOUTH;
        end = RoadDirection.WEST;
        assertEquals(MoveDirection.LEFT, MoveDirection.calcDirection(start, end));
    }

}