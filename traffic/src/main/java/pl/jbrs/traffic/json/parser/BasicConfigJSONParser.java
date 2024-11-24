package pl.jbrs.traffic.json.parser;

import org.json.JSONException;
import org.json.JSONObject;
import pl.jbrs.traffic.json.parser.configoptions.ModelOption;
import pl.jbrs.traffic.json.parser.configoptions.RoadOption;
import pl.jbrs.traffic.json.parser.configoptions.SimulationOption;
import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.road.RoadDirection;

import java.util.List;
import java.util.stream.Stream;

// Class extracts correct values from config.json file
// During the process all invalid keys are omitted
public class BasicConfigJSONParser implements ConfigJSONParser {
    private final JSONObject json;
    public BasicConfigJSONParser(String jsonString) throws JSONException {
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

    // It's a mess, but multiply nested json must be done this way
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

        List<String> laneKeys = Stream.of(LaneDirection.values())
                .map(LaneDirection::toString)
                .toList();

        try {
            JSONObject northRoad = extractKeys(keys, roadsJson.getJSONObject(RoadDirection.NORTH.toString()));
            try {
                JSONObject northLanes = extractKeys(laneKeys, northRoad.getJSONObject(RoadOption.Lanes.toString()));
                northRoad.put(RoadOption.Lanes.toString(), northLanes);
            } catch (JSONException ignored) {}
            roadsJson.put(RoadDirection.NORTH.toString(), northRoad);
        } catch (JSONException ignored) {}

        try {
            JSONObject eastRoad = extractKeys(keys, roadsJson.getJSONObject(RoadDirection.EAST.toString()));
            try {
                JSONObject northLanes = extractKeys(laneKeys, eastRoad.getJSONObject(RoadOption.Lanes.toString()));
                eastRoad.put(RoadOption.Lanes.toString(), northLanes);
            } catch (JSONException ignored) {}
            roadsJson.put(RoadDirection.EAST.toString(), eastRoad);
        } catch (JSONException ignored) {}

        try {
            JSONObject southRoad = extractKeys(keys, roadsJson.getJSONObject(RoadDirection.SOUTH.toString()));
            try {
                JSONObject northLanes = extractKeys(laneKeys, southRoad.getJSONObject(RoadOption.Lanes.toString()));
                southRoad.put(RoadOption.Lanes.toString(), northLanes);
            } catch (JSONException ignored) {}
            roadsJson.put(RoadDirection.SOUTH.toString(), southRoad);
        } catch (JSONException ignored) {}

        try {
            JSONObject westRoad = extractKeys(keys, roadsJson.getJSONObject(RoadDirection.WEST.toString()));
            try {
                JSONObject northLanes = extractKeys(laneKeys, westRoad.getJSONObject(RoadOption.Lanes.toString()));
                westRoad.put(RoadOption.Lanes.toString(), northLanes);
            } catch (JSONException ignored) {}
            roadsJson.put(RoadDirection.WEST.toString(), westRoad);
        } catch (JSONException ignored) {}

        return roadsJson;
    }
}
