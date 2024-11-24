package pl.jbrs.traffic.simulation.result;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import pl.jbrs.traffic.model.Vehicle;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasicSimulationResultTest {
    private static List<Vehicle> vehicles;
    @BeforeAll
    public static void setVehicles() {
        vehicles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            vehicles.add(new Vehicle("v" + i, RoadDirection.SOUTH, RoadDirection.NORTH));
        }
    }

    private JSONObject generateExpected() {
        String jsonString =
                "{" +
                    "\"stepStatuses\":[" +
                        "{" +
                            "\"leftVehicles\":[]" +
                        "}," +
                        "{" +
                            "\"leftVehicles\":[" +
                                "\"v0\"" +
                            "]" +
                        "}," +
                        "{" +
                            "\"leftVehicles\":[]" +
                        "}," +
                        "{" +
                            "\"leftVehicles\":[" +
                                "\"v1\"," +
                                "\"v2\"" +
                            "]" +
                        "}" +
                    "]" +
                "}";

        return new JSONObject(jsonString);
    }
    @Test
    public void basicSimulationResultTest() {
        // given
        BasicSimulationResult basicSimulationResult = new BasicSimulationResult();

        // when
        basicSimulationResult.saveStep();
        basicSimulationResult.addVehicleToCurrentStep(vehicles.get(0));
        basicSimulationResult.saveStep();
        basicSimulationResult.saveStep();
        basicSimulationResult.addVehicleToCurrentStep(vehicles.get(1));
        basicSimulationResult.addVehicleToCurrentStep(vehicles.get(2));
        basicSimulationResult.saveStep();

        // then
        JSONObject actual = basicSimulationResult.getJSONResult();
        JSONObject expected = generateExpected();
        JSONAssert.assertEquals(expected, actual, true);
    }

}