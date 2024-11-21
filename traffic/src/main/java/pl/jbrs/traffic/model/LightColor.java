package pl.jbrs.traffic.model;

public enum LightColor {
    GREEN,
    YELLOW,
    RED,
    RED_YELLOW; // Even tough present, this state is omitted, because it doesn't add any value to the simulation and increases complexity
    // In Poland when green light is about to light up the red and yellow lights are turned on at the same time.
    // When green is about to change to res yellow light is turned on alone.
}
