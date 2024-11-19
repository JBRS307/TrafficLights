package pl.jbrs.traffic.strategy;

import pl.jbrs.traffic.model.road.RoadDirection;
import pl.jbrs.traffic.model.road.Road;

import java.util.Map;

// Strategy decides how the lights are working.
// In general every strategy somehow bases on.
//    - Number of cars waiting on each road in each direction
//    - Road priority (if road is not prioritized then it has default priority of 1, the higher the priority the more improtant the road)
// Every cycle is made of "states". State is a situation when certain lights are set to green.
// When time estimated for the state is up, state changes to the next in the cycle.
// Strategy determines length of each step based on attributes mentioned above

@FunctionalInterface
public interface Strategy {
    void calcNextState(Map<RoadDirection, Road> roadMap);
}
