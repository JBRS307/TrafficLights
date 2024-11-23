package pl.jbrs.traffic.jsonparser.configoptions;

public enum SimulationOption {
    TrafficStrategy;

    @Override
    public String toString() {
        return switch (this) {
            case TrafficStrategy -> "trafficStrategy";
        };
    }
}
