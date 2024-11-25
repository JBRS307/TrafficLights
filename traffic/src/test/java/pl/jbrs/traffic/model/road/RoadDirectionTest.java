package pl.jbrs.traffic.model.road;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoadDirectionTest {
    @Test
    public void toStringTest() {
        // given, when, then
        assertEquals("north", RoadDirection.NORTH.toString());

        // given, when, then
        assertEquals("east", RoadDirection.EAST.toString());

        // given, when, then
        assertEquals("south", RoadDirection.SOUTH.toString());

        // given, when, then
        assertEquals("west", RoadDirection.WEST.toString());
    }

    @Test
    public void fromStringTest() {
        // given, when, then
        assertEquals(RoadDirection.NORTH, RoadDirection.fromString("north"));

        // given, when, then
        assertEquals(RoadDirection.EAST, RoadDirection.fromString("east"));

        // given, when, then
        assertEquals(RoadDirection.SOUTH, RoadDirection.fromString("south"));

        // given, when, then
        assertEquals(RoadDirection.WEST, RoadDirection.fromString("west"));

        // given, when, then
        assertThrows(IllegalArgumentException.class, () -> RoadDirection.fromString("isdgsdgsdg"));
    }

    @Test
    public void nextTest() {
        // given, when, then
        assertEquals(RoadDirection.EAST, RoadDirection.NORTH.next());
        assertEquals(RoadDirection.SOUTH, RoadDirection.EAST.next());
        assertEquals(RoadDirection.WEST, RoadDirection.SOUTH.next());
        assertEquals(RoadDirection.NORTH, RoadDirection.WEST.next());
    }

    @Test
    public void prevTest() {
        // given, when, then
        assertEquals(RoadDirection.WEST, RoadDirection.NORTH.prev());
        assertEquals(RoadDirection.SOUTH, RoadDirection.WEST.prev());
        assertEquals(RoadDirection.EAST, RoadDirection.SOUTH.prev());
        assertEquals(RoadDirection.NORTH, RoadDirection.EAST.prev());
    }
}