package pl.jbrs.traffic.model;

public class TrafficLight {
    private LightColor color;
    private final Lane lane;

    public TrafficLight(Lane lane) {
        this.color = LightColor.RED;
        this.lane = lane;
    }

    public Lane getLane() {
        return lane;
    }
}
