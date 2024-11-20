package pl.jbrs.traffic.configuration;

public class SimulationConfiguration {
    private TrafficStrategy trafficStrategy;

    public SimulationConfiguration() {
        this.trafficStrategy = DefaultSimulationConfiguration.TRAFFIC_STRATEGY;
    }

    public TrafficStrategy getTrafficStrategy() {
        return trafficStrategy;
    }

    public void setTrafficStrategy(TrafficStrategy trafficStrategy) {
        this.trafficStrategy = trafficStrategy;
    }
}
