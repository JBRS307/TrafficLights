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
}
