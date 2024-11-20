package pl.jbrs.traffic.creator;

import pl.jbrs.traffic.configuration.RoadConfiguration;
import pl.jbrs.traffic.model.PedestrianLight;
import pl.jbrs.traffic.model.road.BasicRoad;
import pl.jbrs.traffic.model.road.PedestrianRoad;
import pl.jbrs.traffic.model.road.Road;
import pl.jbrs.traffic.model.road.RoadDirection;

public class RoadCreator {
    private final RoadConfiguration roadConfiguration;
    private final RoadDirection roadDirection;

    private final TrafficLightsCreator trafficLightsCreator;

    public RoadCreator(RoadConfiguration roadConfiguration, RoadDirection roadDirection) {
        this.roadConfiguration = roadConfiguration;
        this.roadDirection = roadDirection;
        this.trafficLightsCreator = new TrafficLightsCreator(roadConfiguration.getLanes());
    }

    private Road createPedestrianRoad() {
        return new PedestrianRoad(roadDirection, trafficLightsCreator.createTrafficLights(), new PedestrianLight(), roadConfiguration.getPriority());
    }

    private Road createBasicRoad() {
        return new BasicRoad(roadDirection, trafficLightsCreator.createTrafficLights(), roadConfiguration.getPriority());
    }

    public Road createRoad() {
        if (roadConfiguration.hasCrosswalk()) {
            return createPedestrianRoad();
        } else {
            return createBasicRoad();
        }
    }
}
