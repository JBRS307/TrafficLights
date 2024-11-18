package pl.jbrs.traffic.model.road;

public enum RoadDirection {
    NORTH, EAST, SOUTH, WEST;

    public static RoadDirection fromString(String direction) {
        return switch (direction.toLowerCase()) {
            case "north" -> NORTH;
            case "east" -> EAST;
            case "south" -> SOUTH;
            case "west" -> WEST;
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        };
    }

    public int toInt() {
        return switch (this) {
            case NORTH -> 0;
            case EAST -> 1;
            case SOUTH -> 2;
            case WEST -> 3;
        };
    }
}
