package pl.jbrs.traffic.creator;

import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.configuration.DefaultRoadConfiguration;
import pl.jbrs.traffic.configuration.RoadConfiguration;
import pl.jbrs.traffic.model.road.BasicRoad;
import pl.jbrs.traffic.model.road.PedestrianRoad;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;

import static org.junit.jupiter.api.Assertions.*;

public class RoadCreatorTest {
    @Test
    public void createRoadTestForBasicRoad() {
        // given
        RoadConfiguration roadConfiguration = new RoadConfiguration(); // In default configuration road doesn't have a crosswalk
        RoadCreator roadCreator = new RoadCreator(roadConfiguration, RoadDirection.NORTH);

        // when
        Road road = roadCreator.createRoad();

        // then
        assertInstanceOf(BasicRoad.class, road);
        assertFalse(road instanceof PedestrianRoad);
        assertEquals(DefaultRoadConfiguration.PRIORITY, road.getPriority());
    }

    @Test
    public void createRoadTestForPedestrianRoad() {
        // given
        RoadConfiguration roadConfiguration = new RoadConfiguration();
        roadConfiguration.setPriority(22);
        roadConfiguration.setCrosswalk(true);
        RoadCreator roadCreator = new RoadCreator(roadConfiguration, RoadDirection.EAST);

        // when
        Road road = roadCreator.createRoad();


        // then
        assertInstanceOf(PedestrianRoad.class, road);
        assertEquals(22, road.getPriority());
    }
}