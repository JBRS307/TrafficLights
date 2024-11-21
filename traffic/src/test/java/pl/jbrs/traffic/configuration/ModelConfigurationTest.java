package pl.jbrs.traffic.configuration;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelConfigurationTest {
    // given
    private ModelConfiguration modelConfiguration;
    @BeforeEach
    public void initModelConfiguration() {
        modelConfiguration = new ModelConfiguration();
    }

    // given
    private static JSONObject sampleJSON;
    @BeforeAll
    public static void initSampleJSON() {
        sampleJSON = new JSONObject();
        sampleJSON.put("stateLength", 34);
        sampleJSON.put("yellowTime", 12);
        sampleJSON.put("minStateLength", -33);
    }

    // It should also write error message to console
    @Test
    public void fromJSONTest() {
        // when
        ModelConfiguration testConfig = ModelConfiguration.fromJSON(sampleJSON);

        // then
        assertEquals(testConfig.getStateLength(), 34);
        assertEquals(testConfig.getYellowTime(), 12);
        assertEquals(testConfig.getMinStateLength(), DefaultModelConfiguration.MIN_STATE_LENGTH);
        assertEquals(testConfig.getPriorityMultiplier(), DefaultModelConfiguration.PRIORITY_MULTIPLIER);

    }

    @Test
    public void setStateLengthTest() {
        // when, then
        assertThrowsExactly(IllegalArgumentException.class, () -> modelConfiguration.setStateLength(0));

        // when
        modelConfiguration.setStateLength(254);
        // then
        assertEquals(254, modelConfiguration.getStateLength());
    }

    @Test
    public void setPriorityMultiplierTest() {
        // when, then
        assertThrowsExactly(IllegalArgumentException.class, () -> modelConfiguration.setPriorityMultiplier(-3));

        // when
        modelConfiguration.setPriorityMultiplier(1111);
        // then
        assertEquals(1111, modelConfiguration.getPriorityMultiplier());
    }

    @Test
    public void setYellowTimeTest() {
        // when, then
        assertThrowsExactly(IllegalArgumentException.class, () -> modelConfiguration.setYellowTime(-22));

        // when
        modelConfiguration.setYellowTime(222);
        // then
        assertEquals(222, modelConfiguration.getYellowTime());
    }

    @Test
    public void setMinStateLengthTest() {
        // when, then
        assertThrowsExactly(IllegalArgumentException.class, () -> modelConfiguration.setMinStateLength(-999));

        // when
        modelConfiguration.setMinStateLength(999);
        // then
        assertEquals(999, modelConfiguration.getMinStateLength());
    }

}