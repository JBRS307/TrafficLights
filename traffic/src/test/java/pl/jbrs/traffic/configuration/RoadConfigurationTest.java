package pl.jbrs.traffic.configuration;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.exception.InvalidRoadConfigurationException;
import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RoadConfigurationTest {
    // given
    private RoadConfiguration roadConfiguration;
    @BeforeEach
    public void initRoadConfiguration() {
        roadConfiguration = new RoadConfiguration();
    }

    // given
    // TODO
//    private static JSONObject sampleJSON;
//    @BeforeAll
//    public static void initSampleJSON() {
//    }
//
//    @Test
//    public void fromJSONTest() {
//        // when
//        Map<RoadDirection, RoadConfiguration> testConfig = RoadConfiguration.fromJSON(sampleJSON);
//    }

    @Test
    public void setPriorityTest() {
        // when, then
        assertThrowsExactly(IllegalArgumentException.class, () -> roadConfiguration.setPriority(0));

        // when
        roadConfiguration.setPriority(22);
        // then
        assertEquals(22, roadConfiguration.getPriority());
    }

    @Test
    public void putToLanesTest() {
        // when, then
        assertThrowsExactly(IllegalArgumentException.class, () -> {
           roadConfiguration.putToLanes(LaneDirection.STRAIGHT, -3);
        });

        // when
        roadConfiguration.putToLanes(LaneDirection.RIGHT, 14);
        // then
        assertEquals(14, roadConfiguration.getLanes().get(LaneDirection.RIGHT));
    }

    @Test
    public void validateTest() {
        // when, then
        assertDoesNotThrow(() -> roadConfiguration.validate());

        // when
        roadConfiguration.putToLanes(LaneDirection.UTURN, 1);
        roadConfiguration.putToLanes(LaneDirection.UTURN_LEFT, 1);
        // then
        assertThrowsExactly(InvalidRoadConfigurationException.class, () -> roadConfiguration.validate());

        // given
        initRoadConfiguration();
        // when
        roadConfiguration.putToLanes(LaneDirection.UTURN_LEFT, 3);
        // then
        assertThrowsExactly(InvalidRoadConfigurationException.class, () -> roadConfiguration.validate());
    }

}