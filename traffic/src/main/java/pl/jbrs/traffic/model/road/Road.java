package pl.jbrs.traffic.model.road;

import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.PedestrianLight;
import pl.jbrs.traffic.model.TrafficLight;
import pl.jbrs.traffic.model.Vehicle;

import java.util.List;
import java.util.Map;

public interface Road {
    void addVehicle(Vehicle v);

    void addPedestrian();

    boolean hasCrosswalk();

    // should be used only when there IS a crosswalk on the road,
    // otherwise there is a risk of NullPointerException
    PedestrianLight getPedestrianLight();

    boolean isPedestrianCrossing();

    void movePedestrians();

    int getPriority();

    RoadDirection getDirection();

    Map<LaneDirection, List<TrafficLight>> getLights();
}
