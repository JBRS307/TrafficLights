package pl.jbrs.traffic.json.parser;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class BasicConfigJSONParserTest {
    // given
    private static BasicConfigJSONParser parser;
    @BeforeAll
    public static void initTestObject() {
        String sampleString =
                "{\n" +
                    "\"stateLength\":12," +
                    "\"yellowTime\":3," +
                    "\"trafficStrategy\":\"oneRoadCycle\"," +
                    "\"roads\":{" +
                        "\"north\":{" +
                            "\"crosswalk\":true," +
                            "\"lanes\":{" +
                                "\"straightLeft\":2," +
                                "\"uturn\":1," +
                                "\"fsdfsdf\":1212" + // some random key to check if it gets kicked out
                            "}" +
                        "}," +
                        "\"east\":{" +
                            "\"crosswalk\":true" +
                        "}" +
                    "}" +
                "}";

        parser = new BasicConfigJSONParser(sampleString);
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
        String jsonString =
                "{" +
                    "\"north\":{" +
                        "\"crosswalk\":true," +
                        "\"lanes\":{" +
                            "\"straightLeft\":2," +
                            "\"uturn\":1," +
                        "}" +
                    "}," +
                    "\"east\":{" +
                        "\"crosswalk\":true" +
                    "}" +
                "}";
        return new JSONObject(jsonString);
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