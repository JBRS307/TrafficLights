# Traffic Lights

Project is a smart traffic lights simulator for 4 way intersection.
Project requires **Java 21+**.


## How to run

To build a project enter the root folder and use
```bash
./gradlew jar
```
command. Then enter the *build/libs* directory
```bash
cd build/libs
```
and run with
```bash
java -jar traffic-1.0.jar <path_to_input> [path_to_output] [path_to_config]
```
Output and config arguments are optional, however they must be provided in order (i.e. if you want to provide config you have to provide output).
Default output is ```output.json``` placed in the same directory as ```jar``` file.

## How it works?

The algorithm counts cars waiting at each road, at each lane. When light is about to change it uses the number of waiting cars to calculate how long next green light cycle should take. Calculation is done by the following formula  
$$T \approx \frac{C_g}{C_r} \cdot T_B$$  
$$C_g = C_{ng} + P \cdot P_m$$  
$$C_r = \left(C_{nr} + P \cdot P_m \right) \div (N - 1)$$  
where
- $T$ is time of the next green cycle rounded to the nearest integer.
- $T_B$ is fixed base value to calculate cycle length
- $C_{ng}$ is a number of cars that are about to get green light
- $C_{nr}$ is a number of cars that are about to get red light
- $P$ is priority of the road that cars on which calculations happens for stand
- $P_m$ is fixed priority multiplier
- $N$ is number of states in the strategy (E.g. *One Road Cycle* has 4 states so $N - 1$ is $3$)

### How exactly does priority work?
$P \cdot P_m$ is a number of artificial cars that are added (or subtracted if priority is negative) to the number of cars waiting on the given road. For strategies that allow cars from different roads at the same time it is used to calculate both $C_g$ and $C_r$.

Each of the 4 roads can have multiple lanes in one of the following directions (from left to right):
- u-turn
- u-turn-left
- straight-left
- straight
- straight-right
- right

**WARNING**: there can be only 1 u-turn lane OR only 1 u-turn-left lane.

When cars have green light they start driving through the intersection. Time for each car to go through intersection is fixed and takes 1 simulation step.

There can be 1 crosswalk on each road. Time that takes pedestrians to cross the road is fixed and takes 2 simulation steps. Whenever there is collision of cars and pedestrians (situations when both cars turning right and pedestrians after the turn have green light are common) all cars wait until pedestrians finish crossing the road.

### Strategies
Strategies that manage light cycles. Currently only one strategy is implemented. 

#### One Road Cycle
Simple strategy that gives green light to all cars on one road and to the lane that turns right on the next road clockwise (i.e. when whole north road has green light, then also lanes that go right, and only right, on the east road have green light). When there is a crosswalk on the previous road clockwise, then it has green light (i. e. green light for north road means green light for pedestrians on west road). Cycle goes clockwise and legnth of each green light is calculated by formula shown above.

## Configuration

Configuration is done with a use of JSON file provided as an argument.

Possible options are:
- State Length - base value that is used to calculate length of each green light cycle in simulation steps. Must be at least 2. JSON key is ```"stateLength"```. Default value is 30.
- Priority Multiplier - number by which priority is multiplied. Must greater than or equal to 0. JSON key is ```"priorityMultiplier"```. Default value is 10.
- Yellow time - how how many simulation steps yellow light after green should be lit up. Must be at least 1. JSON key is ```"yellowTime"```. Default value is 3.
- Minimal state length - minimal length of each green light cycle. It is used when calculated cycle length is shorter than this value. Must be at least 2. JSON key is ```"minStateLength"```. Default value is 5.
- Traffic Strategy - strategy to be used when changing lights. Currently there is only one implemented strategy which is *One Road Cycle*. JSON key is ```"trafficStrategy"```. Only possible value at the moment is ```"oneRoadCycle"``` which is default.

---

Each of the roads is to be configured separately. To configure roads add nested object to config file under key ```"roads"```. Object ```"roads"``` can have up to 4 inner objects, each under one of the keys:
- ```"north"```
- ```"east"```
- ```"south"```
- ```"west"```

**WARNING**: using any of these keys twice will most likely result in whole configuration being seen as invalid which results in simulation running on default settings.

Each road can have following options:
- Crosswalk - indicates if there is crosswalk on the road. Must be either ```true``` or ```false```. JSON key is ```"crosswalk"```. Default value is ```false```.
- Priority - sets priority of the road. This value can be any integer. If it is set over 0 then importance of the road is increased, if it is below 0 then it is decreased. JSON key is ```"priority"```. Default value is 0 for each road.

Number of lanes of each type for each road can also be setup. To do so, add new json object under key ```"lanes"``` to object that represents the road.
- U-turn - Sets number of lanes that allow driver to take a u-turn. This can be either 0 or 1. Must be 0 if u-turn-left is 1. JSON key is ```"uturn"```.
- U-turn-left - Sets number of lanes that allow to turn left or take a u-turn. Must be either 0 or 1. Must be 0 if u-turn is 1. JSON key is ```"uturnLeft"```.
- Left - number of lanes that allow driver to turn left. Must be greater than or equal to 0. JSON key is ```"left"```
- Straight-left - number of lanes to turn left or go straight from. Must be greater than or equal to 0. JSON key is ```"straightLeft"```.
- Straight - number of lanes to go straight from. Must be greater than or equal to 0. JSON key is ```"straight"```
- Straight-right - number of lanes to turn right or go straight from. Must be greater than or equal to 0. JSON key is ```"straightRight"```
- Right - number of lanes to go right from. Must be greater than or equal to 0. JSON key is ```"right"```.

Default lanes for each road are:
- 1 u-turn-left lane
- 1 straight lane
- 1 right lane

Sample config:
```json
{
    "stateLength": 5,
    "roads": {
        "east": {
            "crosswalk": true,
            "lanes": {
                "right": 0
            }
        }
    }
}
```
It will change state length to 5 and, for the eastern road, add a crosswalk and remove all lanes to turn right.

All configuration keys not included in this file and all incorrect values will be omitted and corresponding settings will be kept at default.

## Commands
For the simulation to run there must be a list of commands provided in a file ```input.json``` put in the same directory as ```jar``` file.

Input file should be given in following form
```json
{
    "commands": [
        {
            "type": // ...
            // options
        },
        {
            "type": // ...
            // options
        }
        // ...
    ]
}
```


Available values for ```"type"```:
- ```"addVehicle"``` - this command adds vehicle to a given road. Options of this command are
    - ```"vehicleId"``` - string, id of a vehicle.
    - ```"startRoad"``` - string, either ```"north"```, ```"east"```, ```"south"``` or ```"west"```. The road that vehicle should be placed onto.
    - ```"endRoad"``` - string, values the same as for ```"startRoad"```. Destination of the vehicle.
- ```"addPedestrian"``` - commands that adds pedestrian to cross the road. Options are:
    - ```"road"``` - string, to which road should pedestrian be added. Values the same as for ```"startRoad"```.
- ```"step"``` - commands tells simulation to make a step.

```json
{
    "commands": [
        {
            "type": "addVehicle",
            "vehicleId": "vehicle1",
            "startRoad": "south",
            "endRoad": "north"
        },
        {
            "type": "addVehicle",
            "vehicleId": "vehicle2",
            "startRoad": "north",
            "endRoad": "south"
        },
        {
            "type": "addVehicle",
            "vehicleId": "vehicle3",
            "startRoad": "south",
            "endRoad": "south"
        },
        {
            "type": "addVehicle",
            "vehicleId": "vehicle4",
            "startRoad": "south",
            "endRoad": "east"
        },
        {
            "type": "addVehicle",
            "vehicleId": "vehicle5",
            "startRoad": "east",
            "endRoad": "north"
        },
        {
            "type": "addPedestrian",
            "road": "east"
        },
        {
            "type": "addPedestrian",
            "road": "west"
        },
        {
            "type": "addVehicle",
            "vehicleId": "vehicle1",
            "startRoad": "west",
            "endRoad": "north"
        },
        {
            "type": "step"
        },
        {
            "type": "step"
        },
        {
            "type": "step"
        }
    ]
}
```
Sample input. When combined with sample config it contains mistakes, which will be ommitted.

All commands are executed in the same order they are in the JSON file. Every command with invalid key or value will be ommitted. Commands that try to make illegal moves (i.e. adding vehicle with the same ID, adding pedestrian to the road without crosswalk or adding a vehicle with impossible move) will be ommitted.

An impossible move is a move where a car tries to go some direction, but there are no valid lanes to do so.

When car is added to the road it goes to the lane with least amount of cars. If there are multiple lanes like that it goes to the rightmost lane.

## ```Application```
Class responsible for managing commands and running them on simulation.

Application is created with static method
```java
public static Application fromArgs(String[] args);
```
It created application dependent of args.

Two functions are
```java
public void runSimulation();

public void saveSimulationToJSON();
```
First one runs commands parsed from input file.  
Second one saves current state of simulation into output file.

## Interfaces
Short description of interfaces.

### ```Simulation```
Main interface responsible for simulation management. It has 4, 3 oh them are responsible for commands, 1 for generating result JSON.
```java
void step();

void addPedestrian(RoadDirection roadDirection);

void addVehicle(Vehicle vehicle);
```
Responsible for corresponding commands

```java
JSONObject getSimulationResultJSON();
```
Responsible for generating result as ```JSONObject```.

### ```SimulationCreator```
Creates simulation from provided (or default) config.

```java
Simulation createSimulation();
```

### ```Command```
Command for simulation
```java
void execute(Simulation simulation);
```
Executes given command for provided simulation.

### ```TrafficManager```
Interface for strategies. Manages intersection dependent on implementation.
```java
int nextState();
```
This method changes the state and returns how many steps it should last.
