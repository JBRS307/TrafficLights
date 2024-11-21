package pl.jbrs.traffic.model.road;

public enum RoadDirection {
    NORTH, EAST, SOUTH, WEST;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static RoadDirection fromString(String direction) {
        return switch (direction) {
            case "north" -> NORTH;
            case "east" -> EAST;
            case "south" -> SOUTH;
            case "west" -> WEST;
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        };
    }

    public RoadDirection next() {
        return RoadDirection.fromInt((this.toInt() + 1) % 4);
    }

    public RoadDirection prev() {
        int res = this.toInt() - 1;
        return res > 0 ? RoadDirection.fromInt(res) : RoadDirection.WEST;
    }

    public int toInt() {
        return switch (this) {
            case NORTH -> 0;
            case EAST -> 1;
            case SOUTH -> 2;
            case WEST -> 3;
        };
    }

    public static RoadDirection fromInt(int direction) {
        return switch (direction) {
            case 0 -> NORTH;
            case 1 -> EAST;
            case 2 -> SOUTH;
            case 3 -> WEST;
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        };
    }
}
