package pl.jbrs.traffic.model.road;

import pl.jbrs.traffic.exception.MoveOnRedException;
import pl.jbrs.traffic.model.LaneDirection;
import pl.jbrs.traffic.model.LightColor;
import pl.jbrs.traffic.model.PedestrianLight;
import pl.jbrs.traffic.model.TrafficLight;

import java.util.List;
import java.util.Map;

public class PedestrianRoad extends BasicRoad {
    private int pedestriansWaiting = 0;
    private int pedestriansOnRoad = 0;

    private final PedestrianLight pedestrianLight;
    public PedestrianRoad(RoadDirection direction, Map<LaneDirection, List<TrafficLight>> trafficLights, PedestrianLight pedestrianLight, int priority) {
        super(direction, trafficLights, priority);
        this.pedestrianLight = pedestrianLight;
    }

    public int getPedestriansWaiting() {
        return pedestriansWaiting;
    }

    public int getPedestriansOnRoad() {
        return pedestriansOnRoad;
    }

    @Override
    public PedestrianLight getPedestrianLight() {
        return pedestrianLight;
    }

    @Override
    public boolean hasCrosswalk() {
        return true;
    }

    @Override
    public boolean isPedestrianCrossing() {
        return pedestriansOnRoad > 0;
    }

    // Pedestrians should take 2 steps to cross the road
    // it is possible with this approach
    // Pedestrians on road in current steps are pedestrians waiting in the previous step
    // pedestriansWaiting then goes to 0
    @Override
    public void movePedestrians() {
        if (getPedestrianLight().getColor() == LightColor.RED) {
            throw new MoveOnRedException("Pedestrians on road " + direction + " tried to go on red!");
        }
        pedestriansOnRoad = pedestriansWaiting;
        pedestriansWaiting = 0;
    }

    @Override
    public void addPedestrian() {
        pedestriansWaiting++;
        if (pedestrianLight.getColor() == LightColor.RED) {
            pedestrianLight.setButtonPressed(true);
        }
    }
}
