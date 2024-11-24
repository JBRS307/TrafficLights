package pl.jbrs.traffic;

import pl.jbrs.traffic.exception.MissingInputFileException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Application app;
        try {
            app = Application.getInstance();
            app.runSimulation();
            app.saveResultToJSON();
        } catch (MissingInputFileException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}