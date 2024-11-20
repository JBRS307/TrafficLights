package pl.jbrs.traffic.model;

public class TrafficLight {
    private LightColor color;
    private final Lane lane;

    public TrafficLight(Lane lane) {
        this.color = LightColor.RED;
        this.lane = lane;
    }

    // Quality of Life function to make code a bit shorter
    public int getWaitingCars() {
        return lane.getWaitingCars();
    }

    public void changeColor() {
        color = color.next();
    }

    public LightColor getColor() {
        return color;
    }

    public Lane getLane() {
        return lane;
    }
}
