package pl.jbrs.traffic.simulation.configuration;

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
