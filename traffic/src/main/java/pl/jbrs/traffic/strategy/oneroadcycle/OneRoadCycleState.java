package pl.jbrs.traffic.strategy.oneroadcycle;

// This one is very basic, one whole direction has green
// everyone else has red
public enum OneRoadCycleState {
    NORTH, EAST, SOUTH, WEST;

    public OneRoadCycleState next() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }

    public OneRoadCycleState prev() {
        return switch (this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
        };
    }
}
