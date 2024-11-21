package pl.jbrs.traffic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LaneDirectionTest {
    @Test
    public void fromStringTest() {
        // given, when, then
        assertEquals(LaneDirection.UTURN, LaneDirection.fromString("uturn"));

        // given, when, then
        assertEquals(LaneDirection.UTURN_LEFT, LaneDirection.fromString("uturnLeft"));

        // given, when, then
        assertEquals(LaneDirection.LEFT, LaneDirection.fromString("left"));

        // given, when, then
        assertEquals(LaneDirection.STRAIGHT_LEFT, LaneDirection.fromString("straightLeft"));

        // given, when, then
        assertEquals(LaneDirection.STRAIGHT, LaneDirection.fromString("straight"));

        // given, when, then
        assertEquals(LaneDirection.STRAIGHT_RIGHT, LaneDirection.fromString("straightRight"));

        // given, when, then
        assertEquals(LaneDirection.RIGHT, LaneDirection.fromString("right"));

        // given, when, then
        assertThrows(IllegalArgumentException.class, () -> LaneDirection.fromString("sdfgsdfg"));
    }

    @Test
    public void toStringTest() {
        // given, when, then
        assertEquals("uturn", LaneDirection.UTURN.toString());

        // given, when, then
        assertEquals("uturnLeft", LaneDirection.UTURN_LEFT.toString());

        // given, when, then
        assertEquals("left", LaneDirection.LEFT.toString());

        // given, when, then
        assertEquals("straightLeft", LaneDirection.STRAIGHT_LEFT.toString());

        // given, when, then
        assertEquals("straight", LaneDirection.STRAIGHT.toString());

        // given, when, then
        assertEquals("straightRight", LaneDirection.STRAIGHT_RIGHT.toString());

        // given, when, then
        assertEquals("right", LaneDirection.RIGHT.toString());
    }
}