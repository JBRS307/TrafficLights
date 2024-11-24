package pl.jbrs.traffic.json.parser;

import org.json.JSONObject;

public interface ConfigJSONParser {
    JSONObject modelConfigurationJSON();
    JSONObject simulationConfigurationJSON();
    JSONObject roadConfigurationJSON();
}

