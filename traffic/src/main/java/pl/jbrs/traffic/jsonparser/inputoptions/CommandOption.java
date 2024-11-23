package pl.jbrs.traffic.jsonparser.inputoptions;

public enum CommandOption {
    AddVehicle,
    AddPedestrian,
    Step;

    @Override
    public String toString() {
        return switch (this) {
            case AddVehicle -> "addVehicle";
            case AddPedestrian -> "addPedestrian";
            case Step -> "step";
        };
    }

    public static CommandOption fromString(String option) {
        return switch (option) {
            case "addVehicle" -> AddVehicle;
            case "addPedestrian" -> AddPedestrian;
            case "step" -> Step;
            default -> throw new IllegalArgumentException("Unknown command " + option);
        };
    }
}
