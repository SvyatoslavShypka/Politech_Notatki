package eu.dar3.notatki.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
