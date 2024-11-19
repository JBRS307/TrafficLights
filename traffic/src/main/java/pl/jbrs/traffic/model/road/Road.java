package pl.jbrs.traffic.model.road;

import pl.jbrs.traffic.model.PedestrianLight;
import pl.jbrs.traffic.model.Vehicle;

public interface Road {
    void addVehicle(Vehicle v);

    boolean hasCrosswalk();

    // should be used only when there IS a crosswalk on the road,
    // otherwise there is a risk of NullPointerException
    PedestrianLight getPedestrianLight();

    int getPriority();
}
