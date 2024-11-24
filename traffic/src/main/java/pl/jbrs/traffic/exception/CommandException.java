package pl.jbrs.traffic.exception;

public class CommandException extends RuntimeException {
    public CommandException(String message) {
        super(message);
    }
}
