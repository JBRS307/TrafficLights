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
        return OneRoadCycleState.fromInt((this.toInt() + 1) % 8);
    }

    public OneRoadCycleState prev() {
        int res = this.toInt() - 1;
        return res >= 0 ? OneRoadCycleState.fromInt(res) : OneRoadCycleState.WEST_YELLOW;
    }

    public RoadDirection toRoadDirection() {
        return switch (this) {
            case NORTH, NORTH_YELLOW -> RoadDirection.NORTH;
            case SOUTH, SOUTH_YELLOW -> RoadDirection.SOUTH;
            case EAST, EAST_YELLOW -> RoadDirection.EAST;
            case WEST, WEST_YELLOW -> RoadDirection.WEST;
        };
    }

    public int toInt() {
        return switch (this) {
            case NORTH -> 0;
            case NORTH_YELLOW -> 1;
            case EAST -> 2;
            case EAST_YELLOW -> 3;
            case SOUTH -> 4;
            case SOUTH_YELLOW -> 5;
            case WEST -> 6;
            case WEST_YELLOW -> 7;
        };
    }

    public static OneRoadCycleState fromInt(int value) {
        return switch (value) {
            case 0 -> NORTH;
            case 1 -> NORTH_YELLOW;
            case 2 -> EAST;
            case 3 -> EAST_YELLOW;
            case 4 -> SOUTH;
            case 5 -> SOUTH_YELLOW;
            case 6 -> WEST;
            case 7 -> WEST_YELLOW;
            default -> throw new IllegalArgumentException("OneRoadCycleState value must be between 0 and 7");
        };
    }

    public boolean isYellow() {
        return switch (this) {
            case NORTH, EAST, SOUTH, WEST -> false;
            default -> true;
        };
    }
}
