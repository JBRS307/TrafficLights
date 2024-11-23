package pl.jbrs.traffic.model.road;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jbrs.traffic.exception.MoveOnRedException;
import pl.jbrs.traffic.model.LightColor;
import pl.jbrs.traffic.model.PedestrianLight;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PedestrianRoadTest {
    // given
    private PedestrianRoad pedestrianRoad;
    @BeforeEach
    public void setPedestrianRoad() {
        pedestrianRoad = new PedestrianRoad(RoadDirection.NORTH, Map.of(), new PedestrianLight(), 0);
    }

    @Test
    public void addPedestrianTest() {
        // when, then
        assertEquals(0, pedestrianRoad.getPedestriansWaiting());
        assertFalse(pedestrianRoad.getPedestrianLight().isButtonPressed());

        // when
        pedestrianRoad.addPedestrian();
        assertEquals(1, pedestrianRoad.getPedestriansWaiting());
        assertTrue(pedestrianRoad.getPedestrianLight().isButtonPressed());

    }

    @Test
    public void movePedestriansTest() {
        // when, then
        assertThrowsExactly(MoveOnRedException.class, () -> pedestrianRoad.movePedestrians());

        // given
        pedestrianRoad.addPedestrian();
        pedestrianRoad.getPedestrianLight().setColor(LightColor.GREEN);
        // when
        pedestrianRoad.movePedestrians();
        // then
        assertEquals(0, pedestrianRoad.getPedestriansWaiting());
        assertEquals(1, pedestrianRoad.getPedestriansOnRoad());
        assertTrue(pedestrianRoad.isPedestrianCrossing());

        // when
        pedestrianRoad.movePedestrians();
        // then
        assertEquals(0, pedestrianRoad.getPedestriansWaiting());
        assertEquals(0, pedestrianRoad.getPedestriansOnRoad());
        assertFalse(pedestrianRoad.isPedestrianCrossing());
    }


}