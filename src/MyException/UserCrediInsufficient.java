package MyException;

public class UserCrediInsufficient extends RuntimeException{
    public UserCrediInsufficient(String message) {
        super(message);
    }
}
