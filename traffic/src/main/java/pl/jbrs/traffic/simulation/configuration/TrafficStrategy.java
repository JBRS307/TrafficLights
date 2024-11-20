package pl.jbrs.traffic.simulation.configuration;

public enum TrafficStrategy {
    ONE_ROAD_CYCLE;

    public static TrafficStrategy fromString(String strategy) {
        // Prepared for expanding
        return switch (strategy) {
            case "oneRoadCycle" -> ONE_ROAD_CYCLE;
            default -> throw new IllegalArgumentException("Unknown strategy: " + strategy);
        };
    }
}
