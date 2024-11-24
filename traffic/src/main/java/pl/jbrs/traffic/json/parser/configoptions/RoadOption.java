package pl.jbrs.traffic.json.parser.configoptions;

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
