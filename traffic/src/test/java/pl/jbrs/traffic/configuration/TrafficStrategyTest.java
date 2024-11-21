package pl.jbrs.traffic.configuration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrafficStrategyTest {
    @Test
    public void fromStringTest() {
        String cycle;

        // given
        cycle = "oneRoadCycle";
        // when
        TrafficStrategy strategy = TrafficStrategy.fromString(cycle);
        // then
        assertEquals(TrafficStrategy.ONE_ROAD_CYCLE, strategy);

        // given, when, then
        assertThrows(IllegalArgumentException.class, () -> {
            TrafficStrategy.fromString("fsdfsdf");
        });
    }

}