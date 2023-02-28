package MyException;

public class UserCrediInsufficientException extends RuntimeException{
    public UserCrediInsufficientException(String message) {
        super(message);
    }
}
