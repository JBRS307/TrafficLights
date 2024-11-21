package pl.jbrs.traffic.configuration;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationConfigurationTest {
    // given
    private static JSONObject sampleJSON;
    @BeforeAll
    public static void initSampleJSON() {
        sampleJSON = new JSONObject();
        sampleJSON.put("trafficStrategy", "oneRoadCycle");
    }

    @Test
    public void fromJSONTest() {
        // when
        SimulationConfiguration simulationConfiguration = SimulationConfiguration.fromJSON(sampleJSON);
        // then
        assertEquals(TrafficStrategy.ONE_ROAD_CYCLE, simulationConfiguration.getTrafficStrategy());
    }


  
}