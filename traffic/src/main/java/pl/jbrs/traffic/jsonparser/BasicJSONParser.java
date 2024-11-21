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
                result.put(key, jsonObject.getString(key));
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
        JSONObject roadsJson = json.getJSONObject("roads");

        List<String> directionKeys = Stream.of(RoadDirection.values())
                .map(RoadDirection::toString)
                .toList();

        // Clear incorrect road directions
        roadsJson = extractKeys(directionKeys, roadsJson);

        List<String> keys = Stream.of(RoadOption.values())
                .map(RoadOption::toString)
                .toList();

        JSONObject northLanes = extractKeys(keys, roadsJson.getJSONObject(RoadDirection.NORTH.toString()));
        JSONObject eastLanes = extractKeys(keys, roadsJson.getJSONObject(RoadDirection.EAST.toString()));
        JSONObject westLanes = extractKeys(keys, roadsJson.getJSONObject(RoadDirection.WEST.toString()));
        JSONObject southLanes = extractKeys(keys, roadsJson.getJSONObject(RoadDirection.SOUTH.toString()));

        List<String> laneKeys = Stream.of(LaneDirection.values())
                .map(LaneDirection::toString)
                .toList();

        northLanes = extractKeys(laneKeys, northLanes);
        eastLanes = extractKeys(laneKeys, eastLanes);
        westLanes = extractKeys(laneKeys, westLanes);
        southLanes = extractKeys(laneKeys, southLanes);

        roadsJson.put(RoadDirection.NORTH.toString(), northLanes);
        roadsJson.put(RoadDirection.EAST.toString(), eastLanes);
        roadsJson.put(RoadDirection.WEST.toString(), westLanes);
        roadsJson.put(RoadDirection.SOUTH.toString(), southLanes);

        return roadsJson;
    }
}
