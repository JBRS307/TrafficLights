package pl.jbrs.traffic.json;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter {
    public static void writeJSON(String path, JSONObject json) throws IOException {
        String jsonString = json.toString(4);
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
