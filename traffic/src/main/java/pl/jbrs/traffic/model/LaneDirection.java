package pl.jbrs.traffic.model;

import java.util.List;

public enum LaneDirection {
    UTURN,
    UTURN_LEFT,
    LEFT,
    STRAIGHT_LEFT,
    STRAIGHT,
    STRAIGHT_RIGHT,
    RIGHT;

    public static LaneDirection fromString(String direction) {
        return switch (direction) {
            case "uturn" -> UTURN;
            case "uturnLeft" -> UTURN_LEFT;
            case "left" -> LEFT;
            case "straightLeft" -> STRAIGHT_LEFT;
            case "straight" -> STRAIGHT;
            case "right" -> RIGHT;
            case "straightRight" -> STRAIGHT_RIGHT;
            default -> throw new IllegalArgumentException("Invalid lane direction: " + direction);
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case UTURN -> "uturn";
            case UTURN_LEFT -> "uturnLeft";
            case LEFT -> "left";
            case STRAIGHT_LEFT -> "straightLeft";
            case STRAIGHT -> "straight";
            case STRAIGHT_RIGHT -> "straightRight";
            case RIGHT -> "right";
        };
    }

    // Function gets possible lane direction for given move direction
    // That is for MoveDirection STRAIGHT it will return List.of(STRAIGHT_RIGHT, STRAIGHT, STRAIGHT_LEFT)
    // List will be ordered from rightmost to leftmost direction
    // In case of UTURN List.of(UTURN, UTURN_LEFT) is returned, cecause there can be only ONE uturn line
    // which is either solely for uturn or for uturn and left turn
    public static List<LaneDirection> fromMoveDirection(MoveDirection moveDirection) {
        return switch (moveDirection) {
            case UTURN -> List.of(UTURN, UTURN_LEFT);
            case LEFT -> List.of(STRAIGHT_LEFT, LEFT, UTURN_LEFT);
            case STRAIGHT -> List.of(STRAIGHT_LEFT, STRAIGHT, STRAIGHT_RIGHT);
            case RIGHT -> List.of(STRAIGHT_RIGHT, STRAIGHT);
        };
    }
}
