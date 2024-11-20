package pl.jbrs.traffic.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class TrafficLightTest {
    @Test
    public void changeColorTest() {
        Lane lane = Mockito.mock(Lane.class);
        TrafficLight trafficLight = new TrafficLight(lane);

        // Every light should start with red when created
        assertEquals(LightColor.RED, trafficLight.getColor());

        trafficLight.changeColor();
        assertEquals(LightColor.RED_YELLOW, trafficLight.getColor());

        trafficLight.changeColor();
        assertEquals(LightColor.GREEN, trafficLight.getColor());

        trafficLight.changeColor();
        assertEquals(LightColor.YELLOW, trafficLight.getColor());

        trafficLight.changeColor();
        assertEquals(LightColor.RED, trafficLight.getColor());
    }

}