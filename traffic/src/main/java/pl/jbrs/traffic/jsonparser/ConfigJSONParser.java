package pl.jbrs.traffic.jsonparser;

import org.json.JSONObject;

public interface ConfigJSONParser {
    JSONObject modelConfigurationJSON();
    JSONObject simulationConfigurationJSON();
    JSONObject roadConfigurationJSON();
}

