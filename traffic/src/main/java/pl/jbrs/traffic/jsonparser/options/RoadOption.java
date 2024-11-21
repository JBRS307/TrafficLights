package pl.jbrs.traffic.jsonparser.options;

public enum RoadOption {
    Crosswalk,
    Priority,
    Lanes;

    @Override
    public String toString() {
        return switch (this) {
            case Crosswalk -> "crosswalk";
            case Priority -> "priority";
            case Lanes -> "lanes";
        };
    }
}
