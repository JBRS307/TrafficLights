package pl.jbrs.traffic.jsonparser;

import org.json.JSONObject;

public interface JSONParser {
    JSONObject modelConfigurationJSON();
    JSONObject simulationConfigurationJSON();
    JSONObject roadConfigurationJSON();
}

