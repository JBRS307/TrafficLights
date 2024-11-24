package pl.jbrs.traffic.simulation.result;

import org.json.JSONObject;
import pl.jbrs.traffic.model.Vehicle;

public interface SimulationResult {
    JSONObject getJSONResult();

    void saveStep();

    void addVehicleToCurrentStep(Vehicle vehicle);
}
