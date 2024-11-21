package pl.jbrs.traffic.jsonparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSONReader {
    public static String readFile(String path) throws IOException {
        return Files.readString(Path.of(path));
    }
}
