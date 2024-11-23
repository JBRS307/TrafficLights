package pl.jbrs.traffic.creator;

import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.configuration.ModelConfiguration;
import pl.jbrs.traffic.configuration.SimulationConfiguration;
import pl.jbrs.traffic.manager.TrafficManager;
import pl.jbrs.traffic.manager.oneroadcycle.OneRoadCycleManager;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TrafficManagerCreatorTest {
    @Test
    public void createTrafficManagerTestForOneRoadCycleManager() {
        // given
        TrafficManagerCreator trafficManagerCreator = new TrafficManagerCreator(new SimulationConfiguration(), new ModelConfiguration(), Map.of());

        // when
        TrafficManager trafficManager = trafficManagerCreator.createTrafficManager();

        // then
        assertInstanceOf(OneRoadCycleManager.class, trafficManager);
    }
}