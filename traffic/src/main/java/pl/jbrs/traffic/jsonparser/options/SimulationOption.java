package pl.jbrs.traffic.jsonparser.options;

public enum SimulationOption {
    TrafficStrategy;

    @Override
    public String toString() {
        return switch (this) {
            case TrafficStrategy -> "trafficStrategy";
        };
    }
}
