package pl.jbrs.traffic.model.road;

import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.PedestrianLight;
import pl.jbrs.traffic.model.TrafficLight;
import pl.jbrs.traffic.model.Vehicle;

import java.util.List;
import java.util.Map;

public interface Road {
    void addVehicle(Vehicle v);

    boolean hasCrosswalk();

    // should be used only when there IS a crosswalk on the road,
    // otherwise there is a risk of NullPointerException
    PedestrianLight getPedestrianLight();

    int getPriority();

    Map<LaneDirection, List<TrafficLight>> getLights();
}
