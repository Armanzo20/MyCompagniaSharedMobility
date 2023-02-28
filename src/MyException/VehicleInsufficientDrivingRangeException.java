package MyException;

public class VehicleInsufficientDrivingRangeException extends RuntimeException{
    public VehicleInsufficientDrivingRangeException(String message) {
        super(message);
    }
}
