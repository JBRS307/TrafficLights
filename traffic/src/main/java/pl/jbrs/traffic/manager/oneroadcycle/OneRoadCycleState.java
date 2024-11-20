package pl.jbrs.traffic.manager.oneroadcycle;

import pl.jbrs.traffic.model.road.RoadDirection;

// This one is very basic, one whole direction has green
// everyone else has red
public enum OneRoadCycleState {
    NORTH, // North road and turn right on East road are green
    NORTH_YELLOW,
    EAST, // East road and turn right on the South road are green
    EAST_YELLOW,
    SOUTH, // South road and turn right on the West road are green
    SOUTH_YELLOW,
    WEST, // West road and turn right on the North road are green
    WEST_YELLOW;

    public OneRoadCycleState next() {
        return switch (this) {
            case NORTH -> NORTH_YELLOW;
            case NORTH_YELLOW -> EAST;
            case EAST -> EAST_YELLOW;
            case EAST_YELLOW -> SOUTH;
            case SOUTH -> SOUTH_YELLOW;
            case SOUTH_YELLOW -> WEST;
            case WEST -> WEST_YELLOW;
            case WEST_YELLOW -> NORTH;
        };
    }

    public OneRoadCycleState prev() {
        return switch (this) {
            case WEST_YELLOW -> WEST;
            case WEST -> SOUTH_YELLOW;
            case SOUTH_YELLOW -> SOUTH;
            case SOUTH -> EAST_YELLOW;
            case EAST_YELLOW -> EAST;
            case EAST -> NORTH_YELLOW;
            case NORTH_YELLOW -> NORTH;
            case NORTH -> WEST_YELLOW;
        };
    }

    public RoadDirection toRoadDirection() {
        return switch (this) {
            case NORTH, NORTH_YELLOW -> RoadDirection.NORTH;
            case SOUTH, SOUTH_YELLOW -> RoadDirection.SOUTH;
            case EAST, EAST_YELLOW -> RoadDirection.EAST;
            case WEST, WEST_YELLOW -> RoadDirection.WEST;
        };
    }

    public boolean isYellow() {
        return switch (this) {
            case NORTH, EAST, SOUTH, WEST -> false;
            default -> true;
        };
    }
}
