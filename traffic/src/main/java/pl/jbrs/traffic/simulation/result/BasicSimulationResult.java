package pl.jbrs.traffic.simulation.result;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.jbrs.traffic.model.Vehicle;

public class BasicSimulationResult implements SimulationResult {
    private static final String STEP_STATUSES = "stepStatuses";
    private static final String LEFT_VEHICLES = "leftVehicles";

    private final JSONObject result = new JSONObject();
    private JSONObject currentStepJSON;

    public BasicSimulationResult() {
        result.put(STEP_STATUSES, new JSONArray());
        resetCurrentStepJSON();
    }

    private void resetCurrentStepJSON() {
        currentStepJSON = new JSONObject();
        currentStepJSON.put(LEFT_VEHICLES, new JSONArray());
    }


    @Override
    public JSONObject getJSONResult() {
        return result;
    }

    @Override
    public void saveStep() {
        result.getJSONArray(STEP_STATUSES).put(currentStepJSON);
        resetCurrentStepJSON();
    }

    @Override
    public void addVehicleToCurrentStep(Vehicle vehicle) {
        currentStepJSON.getJSONArray(LEFT_VEHICLES).put(vehicle.id());
    }
}
