package pl.jbrs.traffic.manager.oneroadcycle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OneRoadCycleStateTest {
    @Test
    public void nextTest() {
        // Not all options will be tested, one in the middle and the edge one to check
        // if jump from end to beginning works

        // given, when, then
        assertEquals(OneRoadCycleState.SOUTH, OneRoadCycleState.EAST_YELLOW.next());

        // given, when, then
        assertEquals(OneRoadCycleState.NORTH, OneRoadCycleState.WEST_YELLOW.next());
    }

    @Test
    public void previousTest() {
        // Same situation as with next test

        // given, when, then
        assertEquals(OneRoadCycleState.SOUTH, OneRoadCycleState.SOUTH_YELLOW.prev());

        // given, when, then
        assertEquals(OneRoadCycleState.WEST_YELLOW, OneRoadCycleState.NORTH.prev());
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