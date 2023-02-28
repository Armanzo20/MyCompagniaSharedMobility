package MyException;

public class InsufficientRentalMinutesException extends RuntimeException {
    public InsufficientRentalMinutesException(String message) {
        super(message);
    }
}
