package pl.jbrs.traffic.configuration;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.exception.InvalidRoadConfigurationException;
import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.HashMap;
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
    private static JSONObject sampleJSON;
    @BeforeAll
    public static void initSampleJSON() {
        // lanes
        JSONObject northLanes = new JSONObject();
        northLanes.put("straight", 22);
        northLanes.put("left", 11);

        JSONObject eastLanes = new JSONObject();
        eastLanes.put("right", 33);
        eastLanes.put("straightRight", 111);

        // roads
        JSONObject northRoad = new JSONObject();
        northRoad.put("crosswalk", true);
        northRoad.put("lanes", northLanes);

        JSONObject eastRoad = new JSONObject();
        eastRoad.put("priority", 34);
        eastRoad.put("lanes", eastLanes);

        sampleJSON = new JSONObject();
        sampleJSON.put("north", northRoad);
        sampleJSON.put("east", eastRoad);
    }

    private RoadConfiguration prepareNorthRoadExpected() {
        RoadConfiguration northConfig = new RoadConfiguration();
        northConfig.setCrosswalk(true);
        northConfig.putToLanes(LaneDirection.STRAIGHT, 22);
        northConfig.putToLanes(LaneDirection.LEFT, 11);
        return northConfig;
    }
    private RoadConfiguration prepareEastRoadExpected() {
        RoadConfiguration eastConfig = new RoadConfiguration();
        eastConfig.setPriority(34);
        eastConfig.putToLanes(LaneDirection.RIGHT, 33);
        eastConfig.putToLanes(LaneDirection.STRAIGHT_RIGHT, 111);
        return eastConfig;
    }
    @Test
    public void fromJSONTest() {
        // when
        Map<RoadDirection, RoadConfiguration> testConfig = RoadConfiguration.fromJSON(sampleJSON);

        // then
        Map<RoadDirection, RoadConfiguration> expectedConfig = new HashMap<>();
        expectedConfig.put(RoadDirection.NORTH, prepareNorthRoadExpected());
        expectedConfig.put(RoadDirection.EAST, prepareEastRoadExpected());
        expectedConfig.put(RoadDirection.SOUTH, new RoadConfiguration());
        expectedConfig.put(RoadDirection.WEST, new RoadConfiguration());

        assertEquals(expectedConfig, testConfig);

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