package pl.jbrs.traffic.model;

import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void fromMoveDirectionTest() {
        // given
        List<LaneDirection> expectedRight = List.of(LaneDirection.RIGHT, LaneDirection.STRAIGHT_RIGHT);
        List<LaneDirection> expectedStraight = List.of(LaneDirection.STRAIGHT_RIGHT, LaneDirection.STRAIGHT, LaneDirection.STRAIGHT_LEFT);
        List<LaneDirection> expectedLeft = List.of(LaneDirection.STRAIGHT_LEFT, LaneDirection.LEFT, LaneDirection.UTURN_LEFT);
        List<LaneDirection> expectedUturn = List.of(LaneDirection.UTURN, LaneDirection.UTURN_LEFT);

        // when, then
        assertEquals(expectedRight, LaneDirection.fromMoveDirection(MoveDirection.RIGHT));
        assertEquals(expectedStraight, LaneDirection.fromMoveDirection(MoveDirection.STRAIGHT));
        assertEquals(expectedLeft, LaneDirection.fromMoveDirection(MoveDirection.LEFT));
        assertEquals(expectedUturn, LaneDirection.fromMoveDirection(MoveDirection.UTURN));
    }
}