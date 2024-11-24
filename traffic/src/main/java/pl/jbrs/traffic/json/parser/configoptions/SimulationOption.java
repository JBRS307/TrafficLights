package pl.jbrs.traffic.json.parser.configoptions;

public enum SimulationOption {
    TrafficStrategy;

    @Override
    public String toString() {
        return switch (this) {
            case TrafficStrategy -> "trafficStrategy";
        };
    }
}
