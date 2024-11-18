package pl.jbrs.traffic.model;

public enum LightColor {
    GREEN,
    YELLOW,
    RED,
    RED_YELLOW;
    // In Poland when green light is about to light up the red and yellow lights are turned on at the same time.
    // When green is about to change to res yellow light is turned on alone.
}
