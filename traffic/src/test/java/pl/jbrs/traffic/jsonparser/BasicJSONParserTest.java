package pl.jbrs.traffic.jsonparser;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasicJSONParserTest {
    // given
    private static BasicJSONParser parser;
    @BeforeAll
    public static void initTestObject() {
        String sampleString =
                "{\n" +
                    "\"stateLength\":12," +
                    "\"yellowTime\":3," +
                    "\"trafficStrategy\":\"oneRoadCycle\"," +
                    "\"crosswalk\":true," +
                    "\"lanes\":{" +
                        "\"north\":{" +
                            "\"straightLeft\":2," +
                            "\"uturn\":1," +
                            "\"fsdfsdf\":1212" + // some random key to check if it gets kicked out
                        "}" +
                    "}" +
                "}";

        parser = new BasicJSONParser(sampleString);
    }

    @Test
    public void testModelConfigurationJSON() {
        // when
        JSONObject result = parser.modelConfigurationJSON();

        // then
        JSONObject expected = new JSONObject();
        expected.put("stateLength", 12);
        expected.put("yellowTime", 3);

        JSONAssert.assertEquals(expected, result, true);
    }

    @Test
    public void testSimulationConfigurationJSON() {
        // when
        JSONObject result = parser.simulationConfigurationJSON();

        // then
        JSONObject expected = new JSONObject();
        expected.put("trafficStrategy", "oneRoadCycle");

        JSONAssert.assertEquals(expected, result, true);
    }

    private JSONObject buildExpectedForRoadConfigurationTest() {
        JSONObject northernLane = new JSONObject();
        northernLane.put("uturn", 1);
        northernLane.put("straightLeft", 2);

        JSONObject lanes = new JSONObject();
        lanes.put("north", northernLane);

        JSONObject result = new JSONObject();
        result.put("crosswalk", true);
        result.put("lanes", lanes);
        return result;
    }
    @Test
    public void testRoadConfigurationJSON() {
        // when
        JSONObject result = parser.roadConfigurationJSON();

        // then
        JSONObject expected = buildExpectedForRoadConfigurationTest();
        JSONAssert.assertEquals(expected, result, true);
    }
}