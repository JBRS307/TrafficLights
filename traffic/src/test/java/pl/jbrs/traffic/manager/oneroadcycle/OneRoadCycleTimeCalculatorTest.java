package pl.jbrs.traffic.manager.oneroadcycle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.jbrs.traffic.configuration.ModelConfiguration;
import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.TrafficLight;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OneRoadCycleTimeCalculatorTest {
    private static OneRoadCycleTimeCalculator calculator;
    private static ModelConfiguration configuration;
    @BeforeAll
    public static void initCalculator() {
        configuration = new ModelConfiguration();
        calculator = new OneRoadCycleTimeCalculator(configuration);
    }

    private Map<LaneDirection, List<TrafficLight>> generateTrafficLights(List<Integer> waitingCars) {
        List<TrafficLight> trafficLights = new ArrayList<>();
        for (int cars : waitingCars) {
            TrafficLight mock = Mockito.mock(TrafficLight.class);
            Mockito.when(mock.getWaitingCars()).thenReturn(cars);
            trafficLights.add(mock);
        }
        Map<LaneDirection, List<TrafficLight>> result = new HashMap<>();
        result.put(LaneDirection.STRAIGHT, trafficLights);
        return result;
    }
    private Map<RoadDirection, Road> prepareRoadMap() {
        Road northRoad = Mockito.mock(Road.class);
        Road southRoad = Mockito.mock(Road.class);
        Road eastRoad = Mockito.mock(Road.class);
        Road westRoad = Mockito.mock(Road.class);

        List<Integer> northCars = List.of(21, 22, 23);
        List<Integer> eastCars = List.of(24, 25, 26);
        List<Integer> southCars = List.of(27, 28, 29);
        List<Integer> westCars = List.of(30, 31, 32);

        Map<LaneDirection, List<TrafficLight>> northLanes = generateTrafficLights(northCars);
        Map<LaneDirection, List<TrafficLight>> eastLanes = generateTrafficLights(eastCars);
        Map<LaneDirection, List<TrafficLight>> southLanes = generateTrafficLights(southCars);
        Map<LaneDirection, List<TrafficLight>> westLanes = generateTrafficLights(westCars);

        Mockito.when(northRoad.getLights()).thenReturn(northLanes);
        Mockito.when(southRoad.getLights()).thenReturn(southLanes);
        Mockito.when(eastRoad.getLights()).thenReturn(eastLanes);
        Mockito.when(westRoad.getLights()).thenReturn(westLanes);

        Mockito.when(northRoad.getPriority()).thenReturn(3);
        Mockito.when(southRoad.getPriority()).thenReturn(3);
        Mockito.when(eastRoad.getPriority()).thenReturn(1);
        Mockito.when(westRoad.getPriority()).thenReturn(1);

        Map<RoadDirection, Road> roadMap = new HashMap<>();
        roadMap.put(RoadDirection.NORTH, northRoad);
        roadMap.put(RoadDirection.SOUTH, southRoad);
        roadMap.put(RoadDirection.EAST, eastRoad);
        roadMap.put(RoadDirection.WEST, westRoad);

        return roadMap;
    }
    @Test
    public void calcNextStepTest() {
        OneRoadCycleState state;

        // given
        state = OneRoadCycleState.NORTH;

        // when, then
        assertEquals(configuration.getYellowTime(), calculator.calcNextStepLength(Map.of(), state));

        // given
        state = OneRoadCycleState.WEST_YELLOW;
        Map<RoadDirection, Road> roadMap = prepareRoadMap();
        // when
        int stateTime = calculator.calcNextStepLength(roadMap, state);
        // then
        int expectedTime = 29; // Counted manually
        assertEquals(expectedTime, stateTime);

    }

}