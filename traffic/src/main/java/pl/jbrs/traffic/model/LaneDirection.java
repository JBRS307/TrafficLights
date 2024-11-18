package pl.jbrs.traffic.model;

public enum LaneDirection {
    UTURN,
    UTURN_LEFT,
    LEFT,
    STRAIGHT_LEFT,
    STRAIGHT,
    STRAIGHT_RIGHT,
    RIGHT;

    public LaneDirection fromString(String direction) {
        return switch (direction) {
            case "uturn" -> UTURN;
            case "uturnLeft" -> UTURN_LEFT;
            case "left" -> LEFT;
            case "straightLeft" -> STRAIGHT_LEFT;
            case "straight" -> STRAIGHT;
            case "right" -> RIGHT;
            case "straightRight" -> STRAIGHT_RIGHT;
            default -> null;
        };
    }
}
