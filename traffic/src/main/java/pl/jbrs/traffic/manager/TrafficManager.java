package pl.jbrs.traffic.manager;

import pl.jbrs.traffic.model.Vehicle;

// Each manager is realizes separate strategy.
// Parts of the strategy are
//     - Manager - main interface
//     - Calculator - which calculates time of the next state (every manager has it's own)
//       which is outsourced to a different class to split responsibilities
//     - State - a step in a traffic lights cycle

public interface TrafficManager {
    // Changes state end returns time of the new state
    int nextState();
}
