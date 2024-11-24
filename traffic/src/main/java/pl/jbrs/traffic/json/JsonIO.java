package pl.jbrs.traffic.json;

import org.json.JSONObject;
import pl.jbrs.traffic.DefaultPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class jsonIO {
    private String inputPath;
    private String outputPath;
    private String configPath;

    public jsonIO(String inputPath, String outputPath, String configPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.configPath = configPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public jsonIO() {
        this(DefaultPaths.INPUT, DefaultPaths.OUTPUT, DefaultPaths.CONFIG);
    }

    public String readInput() throws IOException {
        return Files.readString(Path.of(inputPath));
    }

    public String readConfig() throws IOException {
        return Files.readString(Path.of(configPath));
    }

    public void writeOutput(JSONObject json) throws IOException {
        Files.writeString(Path.of(outputPath), json.toString(4));
    }
}