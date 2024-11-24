package pl.jbrs.traffic.model;

import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.Map;

public record Vehicle(String id, RoadDirection startRoad, RoadDirection endRoad) {
    public boolean canDrive(Map<RoadDirection, Road> greenPedestrianRoads) {
        Road myRoad = greenPedestrianRoads.get(endRoad);
        if (myRoad == null) {
            return true;
        }
        return myRoad.isPedestrianCrossing();
    }
}
