package pl.jbrs.traffic.model;

import pl.jbrs.traffic.model.road.RoadDirection;

public enum MoveDirection {
    LEFT,
    STRAIGHT,
    RIGHT,
    UTURN;

    public static MoveDirection calcDirection(RoadDirection start, RoadDirection end) {
        return switch (end.toInt() - start.toInt()) {
            case 0 -> UTURN;
            case 2, -2 -> STRAIGHT;
            case 1, -3 -> RIGHT;
            case -1, 3 -> LEFT;
            default -> throw new IllegalStateException("Unexpected value: " + (end.toInt() - start.toInt()));
        };

    }
}
