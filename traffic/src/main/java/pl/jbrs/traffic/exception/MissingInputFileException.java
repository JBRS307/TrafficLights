package pl.jbrs.traffic.exception;

public class MissingInputFileException extends RuntimeException {
    public MissingInputFileException(String message) {
        super(message);
    }
}
