package pl.jbrs.traffic.configuration;

import org.json.JSONException;
import org.json.JSONObject;
import pl.jbrs.traffic.json.parser.configoptions.SimulationOption;

public class SimulationConfiguration {
    private TrafficStrategy trafficStrategy;

    public SimulationConfiguration() {
        this.trafficStrategy = DefaultSimulationConfiguration.TRAFFIC_STRATEGY;
    }

    public static SimulationConfiguration fromJSON(JSONObject json) {
        SimulationConfiguration simulationConfiguration = new SimulationConfiguration();
        try {
            simulationConfiguration.setTrafficStrategy(TrafficStrategy.fromString(json.getString(SimulationOption.TrafficStrategy.toString())));
        } catch (JSONException ignored) {
            // NOTHING HERE
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.out.println("Traffic strategy not set, default value kept!");
        }
        return simulationConfiguration;
    }

    public TrafficStrategy getTrafficStrategy() {
        return trafficStrategy;
    }

    public void setTrafficStrategy(TrafficStrategy trafficStrategy) {
        this.trafficStrategy = trafficStrategy;
    }
}
