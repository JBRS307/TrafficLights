package pl.jbrs.traffic.strategy;

// Strategy decides how the lights are working.
// In general every strategy somehow bases on.
//    - Number of cars waiting on each road in each direction
//    - Road priority (if road is not prioritized then it has default priority of 1, the higher the priority the more improtant the road)
// Every cycle is made of "states". State is a situation when certain lights are set to green.
// When time estimated for the state is up, state changes to the next in the cycle.
// Strategy determines length of each step based on attributes mentioned above


// Each strategy calculates time based on the number of cars at the moment
// Cars incoming while the light is green are not taken into consideration,
// time of green light doesn't change dynamically.
@FunctionalInterface
public interface Strategy {
    int calcNextStateTime();
}
