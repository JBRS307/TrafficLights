package pl.jbrs.traffic.exception;

public class VehicleExistsException extends CommandException {
    public VehicleExistsException(String message) {
        super(message);
    }
}
