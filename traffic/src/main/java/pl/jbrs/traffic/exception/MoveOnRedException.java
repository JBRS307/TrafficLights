package pl.jbrs.traffic.exception;

// This exception is an indicator, that something is wrong with traffic strategy, because it
// should be never thrown on working ones
public class MoveOnRedException extends RuntimeException {
    public MoveOnRedException(String message) {
        super(message);
    }
}
