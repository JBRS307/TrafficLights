package pl.jbrs.traffic.jsonparser.options;

public enum LaneOption {
    Uturn,
    Left,
    StraightLeft,
    Straight,
    StraightRight,
    Right;

    @Override
    public String toString() {
        return switch (this) {
            case Uturn -> "uturn";
            case Left -> "left";
            case StraightLeft -> "straightLeft";
            case Straight -> "straight";
            case StraightRight -> "straightRight";
            case Right -> "right";
        };
    }
}
