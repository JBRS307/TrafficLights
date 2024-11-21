package pl.jbrs.traffic.model;

import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.model.road.RoadDirection;

import static org.junit.jupiter.api.Assertions.*;

public class MoveDirectionTest {

    @Test
    void calcDirectionTest() {
        RoadDirection start, end;
        MoveDirection result;

        // given
        start = RoadDirection.NORTH;
        end = RoadDirection.SOUTH;
        // when
        result = MoveDirection.calcDirection(start, end);
        // then
        assertEquals(MoveDirection.STRAIGHT, result);

        // given
        start = RoadDirection.EAST;
        end = RoadDirection.NORTH;
        // when
        result = MoveDirection.calcDirection(start, end);
        // then
        assertEquals(MoveDirection.RIGHT, result);

        // given
        start = RoadDirection.NORTH;
        end = RoadDirection.WEST;
        // when
        result = MoveDirection.calcDirection(start, end);
        // then
        assertEquals(MoveDirection.RIGHT, result);

        // given
        start = RoadDirection.WEST;
        end = RoadDirection.WEST;
        // when
        result = MoveDirection.calcDirection(start, end);
        // then
        assertEquals(MoveDirection.UTURN, result);

        // given
        start = RoadDirection.WEST;
        end = RoadDirection.NORTH;
        // when
        result = MoveDirection.calcDirection(start, end);
        // then
        assertEquals(MoveDirection.LEFT, result);

        // given
        start = RoadDirection.SOUTH;
        end = RoadDirection.WEST;
        // when
        result = MoveDirection.calcDirection(start, end);
        // then
        assertEquals(MoveDirection.LEFT, result);
    }

}