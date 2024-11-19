package pl.jbrs.traffic.model;

import pl.jbrs.traffic.model.road.RoadDirection;

public record Vehicle(String id, RoadDirection startRoad, RoadDirection endRoad) {
}
