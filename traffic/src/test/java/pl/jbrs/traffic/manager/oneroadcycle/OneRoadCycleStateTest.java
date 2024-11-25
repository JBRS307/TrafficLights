package pl.jbrs.traffic.manager.oneroadcycle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OneRoadCycleStateTest {
    @Test
    public void nextTest() {
        // given, when, then
        assertEquals(OneRoadCycleState.NORTH_YELLOW, OneRoadCycleState.NORTH.next());
        assertEquals(OneRoadCycleState.EAST, OneRoadCycleState.NORTH_YELLOW.next());
        assertEquals(OneRoadCycleState.EAST_YELLOW, OneRoadCycleState.EAST.next());
        assertEquals(OneRoadCycleState.SOUTH, OneRoadCycleState.EAST_YELLOW.next());
        assertEquals(OneRoadCycleState.SOUTH_YELLOW, OneRoadCycleState.SOUTH.next());
        assertEquals(OneRoadCycleState.WEST, OneRoadCycleState.SOUTH_YELLOW.next());
        assertEquals(OneRoadCycleState.WEST_YELLOW, OneRoadCycleState.WEST.next());
        assertEquals(OneRoadCycleState.NORTH, OneRoadCycleState.WEST_YELLOW.next());
    }

    @Test
    public void previousTest() {
        // given, when, then
        assertEquals(OneRoadCycleState.WEST_YELLOW, OneRoadCycleState.NORTH.prev());
        assertEquals(OneRoadCycleState.WEST, OneRoadCycleState.WEST_YELLOW.prev());
        assertEquals(OneRoadCycleState.SOUTH_YELLOW, OneRoadCycleState.WEST.prev());
        assertEquals(OneRoadCycleState.SOUTH, OneRoadCycleState.SOUTH_YELLOW.prev());
        assertEquals(OneRoadCycleState.EAST_YELLOW, OneRoadCycleState.SOUTH.prev());
        assertEquals(OneRoadCycleState.EAST, OneRoadCycleState.EAST_YELLOW.prev());
        assertEquals(OneRoadCycleState.NORTH_YELLOW, OneRoadCycleState.EAST.prev());
        assertEquals(OneRoadCycleState.NORTH, OneRoadCycleState.NORTH_YELLOW.prev());
    }

    @Test
    public void fromIntTest() {
        // Mosty to check if exception is thren correctly

        // given, when, then
        assertEquals(OneRoadCycleState.NORTH, OneRoadCycleState.fromInt(0));

        // given, when, then
        assertThrowsExactly(IllegalArgumentException.class, () -> OneRoadCycleState.fromInt(-1));

        // given, when, then
        assertThrowsExactly(IllegalArgumentException.class, () -> OneRoadCycleState.fromInt(8));
    }

}