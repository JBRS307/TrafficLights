package pl.jbrs.traffic.jsonparser;

import org.json.JSONException;
import org.json.JSONObject;
import pl.jbrs.traffic.jsonparser.options.ModelOption;
import pl.jbrs.traffic.jsonparser.options.RoadOption;
import pl.jbrs.traffic.jsonparser.options.SimulationOption;
import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.List;
import java.util.stream.Stream;

// Class extracts correct values from config.json file
// During the process all invalid keys are omitted
public class BasicJSONParser implements JSONParser {
    private final JSONObject json;
    public BasicJSONParser(String jsonString) throws JSONException {
        this.json = new JSONObject(jsonString);
    }

    private JSONObject extractKeys(List<String> keys, JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        for (String key : jsonObject.keySet()) {
            if (keys.contains(key)) {
                result.put(key, jsonObject.get(key));
            }
        }
        return result;
    }

    @Override
    public JSONObject modelConfigurationJSON() {
        List<String> keys = Stream.of(ModelOption.values())
            .map(ModelOption::toString)
            .toList();
        return extractKeys(keys, this.json);
    }

    @Override
    public JSONObject simulationConfigurationJSON() {
        List<String> keys = Stream.of(SimulationOption.values())
                .map(SimulationOption::toString)
                .toList();
        return extractKeys(keys, this.json);
    }

    // JSONArray for roads should be under
    // key "roads"
    @Override
    public JSONObject roadConfigurationJSON() {
        List<String> roadKeys = Stream.of(RoadOption.values())
                .map(RoadOption::toString)
                .toList();

        JSONObject roadsJson = extractKeys(roadKeys, this.json);

        List<String> directionKeys = Stream.of(RoadDirection.values())
                .map(RoadDirection::toString)
                .toList();

        // Clear incorrect road directions
        JSONObject lanesJson;
        try {
            lanesJson = extractKeys(directionKeys, roadsJson.getJSONObject(RoadOption.Lanes.toString()));
        } catch (JSONException e) {
            return roadsJson;
        }

        List<String> laneKeys = Stream.of(LaneDirection.values())
                        .map(LaneDirection::toString)
                        .toList();

        roadsJson.remove(RoadOption.Lanes.toString());
        JSONObject lanesResult = new JSONObject();
        try {
            lanesResult.put(RoadDirection.NORTH.toString(), extractKeys(laneKeys, lanesJson.getJSONObject(RoadDirection.NORTH.toString())));
        } catch (JSONException ignored) {}
        try {
            lanesResult.put(RoadDirection.EAST.toString(), extractKeys(laneKeys, lanesJson.getJSONObject(RoadDirection.EAST.toString())));
        } catch (JSONException ignored) {}
        try {
            lanesResult.put(RoadDirection.SOUTH.toString(), extractKeys(laneKeys, lanesJson.getJSONObject(RoadDirection.SOUTH.toString())));
        } catch (JSONException ignored) {}
        try {
            lanesResult.put(RoadDirection.WEST.toString(), extractKeys(laneKeys, lanesJson.getJSONObject(RoadDirection.WEST.toString())));
        } catch (JSONException ignored) {}

        if (!lanesResult.isEmpty()) {
            roadsJson.put(RoadOption.Lanes.toString(), lanesResult);
        }
        return roadsJson;
    }
}
