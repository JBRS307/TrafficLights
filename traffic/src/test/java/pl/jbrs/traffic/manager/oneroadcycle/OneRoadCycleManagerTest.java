package pl.jbrs.traffic.manager.oneroadcycle;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.jbrs.traffic.configuration.ModelConfiguration;
import pl.jbrs.traffic.model.*;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class OneRoadCycleManagerTest {


    private Map<LaneDirection, List<TrafficLight>> generateTrafficLights(List<Integer> laneCars) {
        Map<LaneDirection, List<TrafficLight>> trafficLights = new HashMap<>();

        // for convenience, to not write the same code thrice
        List<LaneDirection> directions = List.of(LaneDirection.LEFT, LaneDirection.STRAIGHT, LaneDirection.RIGHT);
        var directionsIterator = directions.iterator();

        for (int cars : laneCars) {
            Lane lane = Mockito.mock(Lane.class);
            Mockito.when(lane.getWaitingCars()).thenReturn(cars);
            trafficLights.put(directionsIterator.next(), List.of(new TrafficLight(lane)));
        }
        return trafficLights;
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

        PedestrianLight northPedestrianLight = new PedestrianLight();
        Mockito.when(northRoad.getPedestrianLight()).thenReturn(northPedestrianLight);
        Mockito.when(northRoad.hasCrosswalk()).thenReturn(true);

        PedestrianLight westPedestrianLight = new PedestrianLight();
        Mockito.when(westRoad.getPedestrianLight()).thenReturn(westPedestrianLight);
        Mockito.when(westRoad.hasCrosswalk()).thenReturn(true);

        Map<RoadDirection, Road> roadMap = new HashMap<>();
        roadMap.put(RoadDirection.NORTH, northRoad);
        roadMap.put(RoadDirection.SOUTH, southRoad);
        roadMap.put(RoadDirection.EAST, eastRoad);
        roadMap.put(RoadDirection.WEST, westRoad);

        return roadMap;
    }
    @Test
    public void nextStateTest() {
        // given
        Map<RoadDirection, Road> roadMap = prepareRoadMap();
        OneRoadCycleManager oneRoadCycleManager = new OneRoadCycleManager(roadMap, new ModelConfiguration());

        // this light is green when north road has green light
        roadMap.get(RoadDirection.WEST).getPedestrianLight().setColor(LightColor.GREEN);

        List<TrafficLight> northLights = roadMap.get(RoadDirection.NORTH)
                .getLights()
                .values()
                .stream()
                .flatMap(List::stream)
                .toList();

        List<TrafficLight> eastLights = roadMap.get(RoadDirection.EAST)
                .getLights()
                .values()
                .stream()
                .flatMap(List::stream)
                .toList();

        List<TrafficLight> southLights = roadMap.get(RoadDirection.SOUTH)
                .getLights()
                .values()
                .stream()
                .flatMap(List::stream)
                .toList();

        List<TrafficLight> westLight = roadMap.get(RoadDirection.WEST)
                .getLights()
                .values()
                .stream()
                .flatMap(List::stream)
                .toList();

        // when
        oneRoadCycleManager.nextState();

        // then
        northLights.forEach(light -> {
            assertEquals(LightColor.YELLOW, light.getColor());
        });
        eastLights.forEach(light -> {
            assertEquals(LightColor.RED, light.getColor());
        });
        southLights.forEach(light -> {
            assertEquals(LightColor.RED, light.getColor());
        });
        westLight.forEach(light -> {
            assertEquals(LightColor.RED, light.getColor());
        });
        assertEquals(LightColor.RED, roadMap.get(RoadDirection.WEST).getPedestrianLight().getColor());


        // given
        // simulate someone pressing button on the signaler, that should turn green next step
        roadMap.get(RoadDirection.NORTH).getPedestrianLight().setButtonPressed(true);

        // when
        oneRoadCycleManager.nextState();

        // then
        List<TrafficLight> southShouldBeGreen = roadMap.get(RoadDirection.SOUTH)
                        .getLights()
                        .get(LaneDirection.RIGHT);

        List<TrafficLight> southShouldBeRed = new ArrayList<>();
        southShouldBeRed.addAll(roadMap.get(RoadDirection.SOUTH)
                        .getLights()
                        .get(LaneDirection.LEFT));
        southShouldBeRed.addAll(roadMap.get(RoadDirection.SOUTH)
                                    .getLights()
                                    .get(LaneDirection.STRAIGHT));

        northLights.forEach(light -> {
            assertEquals(LightColor.RED, light.getColor());
        });
        eastLights.forEach(light -> {
            assertEquals(LightColor.GREEN, light.getColor());
        });
        southShouldBeRed.forEach(light -> {
            assertEquals(LightColor.RED, light.getColor());
        });
        southShouldBeGreen.forEach(light -> {
            assertEquals(LightColor.GREEN, light.getColor());
        });
        westLight.forEach(light -> {
            assertEquals(LightColor.RED, light.getColor());
        });
        assertEquals(LightColor.GREEN, roadMap.get(RoadDirection.NORTH).getPedestrianLight().getColor());
    }

}