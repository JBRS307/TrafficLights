package pl.jbrs.traffic.model.road;

import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.exception.NoCrosswalkException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BasicRoadTest {
    @Test
    public void testPedestrianMethodThrows() {
        // given
        Road road = new BasicRoad(RoadDirection.NORTH, Map.of(), 0);

        // when, then
        assertThrowsExactly(NoCrosswalkException.class, road::addPedestrian);

        // when, then
        assertThrowsExactly(NoCrosswalkException.class, road::movePedestrians);
    }
}