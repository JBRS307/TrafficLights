package pl.jbrs.traffic.creator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.TrafficLight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TrafficLightsCreatorTest {
    // given
    private static Map<LaneDirection, Integer> lanes;
    private static TrafficLightsCreator trafficLightsCreator;
    @BeforeAll
    public static void setUp() {
        List<LaneDirection> directions = List.of(LaneDirection.LEFT, LaneDirection.STRAIGHT, LaneDirection.RIGHT);

        lanes = new HashMap<>();
        lanes.put(LaneDirection.LEFT, 1);
        lanes.put(LaneDirection.STRAIGHT, 2);
        lanes.put(LaneDirection.RIGHT, 3);

        trafficLightsCreator = new TrafficLightsCreator(lanes);
    }

    @Test
    public void createTrafficLightsTest() {
        // when
        Map<LaneDirection, List<TrafficLight>> trafficLights = trafficLightsCreator.createTrafficLights();

        // then
        assertEquals(1, trafficLights.get(LaneDirection.LEFT).size());
        assertEquals(2, trafficLights.get(LaneDirection.STRAIGHT).size());
        assertEquals(3, trafficLights.get(LaneDirection.RIGHT).size());
    }
}