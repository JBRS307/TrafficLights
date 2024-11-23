package pl.jbrs.traffic.jsonparser.configoptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationOptionTest {
    @Test
    public void toStringTest() {
        // given, when, then
        assertEquals("trafficStrategy", SimulationOption.TrafficStrategy.toString());
    }

}