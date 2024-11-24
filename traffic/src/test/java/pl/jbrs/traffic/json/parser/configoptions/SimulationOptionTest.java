package pl.jbrs.traffic.json.parser.configoptions;

import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.json.parser.configoptions.SimulationOption;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationOptionTest {
    @Test
    public void toStringTest() {
        // given, when, then
        assertEquals("trafficStrategy", SimulationOption.TrafficStrategy.toString());
    }

}