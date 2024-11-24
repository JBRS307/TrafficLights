package pl.jbrs.traffic.json.parser.inputoptions;

public enum AddVehicleOption {
    VehicleID,
    StartRoad,
    EndRoad;

    @Override
    public String toString() {
        return switch (this) {
            case VehicleID -> "vehicleId";
            case StartRoad -> "startRoad";
            case EndRoad -> "endRoad";
        };
    }

    public static AddVehicleOption fromString(String option) {
        return switch (option) {
            case "vehicleId" -> AddVehicleOption.VehicleID;
            case "startRoad" -> AddVehicleOption.StartRoad;
            case "endRoad" -> AddVehicleOption.EndRoad;
            default -> throw new IllegalArgumentException("Unknown option: " + option);
        };
    }
}
