package entities.rental;

import Database.Database;
import MyException.VehicleInsufficientDrivingRangeException;
import MyException.VehicleNotAvailableException;
import entities.position.Position;
import entities.vehicle.base_motor_vehicles.MotorVehicle;
import entities.vehicle.base_vehicles.Vehicle;

import java.time.LocalDateTime;
import java.util.UUID;


public class RentalInProgress {

    protected UUID rentalID;
    protected UUID userID;
    protected UUID vehicleID;
    protected LocalDateTime rentalStartTime;
    protected Position rentalStartPosition;


    public RentalInProgress(UUID userID, UUID vehicleID,UUID rentalID) throws VehicleNotAvailableException, VehicleInsufficientDrivingRangeException {
        Vehicle vehicle = Database.getUuidVehiclehashMap().get(vehicleID);
        if (vehicle instanceof MotorVehicle) {
            if (((MotorVehicle) vehicle).isInsufficientDrivingRange()) {
                throw new VehicleInsufficientDrivingRangeException("The vehicle with the ID number " + vehicleID + " does not have enough driving range for rental");
            }
        }
        if (!vehicle.isAvailable()) {
            throw new VehicleNotAvailableException("The vehicle with the ID number " + vehicleID + " is currently not available for rental");
        }
        this.rentalID = rentalID; // lo gestico fuori dalla classe per poter assegnare lo stesso id all noleggio terminato;
        this.userID = userID;
        this.vehicleID = vehicleID;
        this.rentalStartPosition = vehicle.getGeographicPosition();
        this.rentalStartTime = LocalDateTime.now();
        vehicle.setAvailable(false);
        Database.getUuidRentalInProgresslHashMap().put(this.rentalID,this);
    }

    public RentalInProgress(RentalInProgress rentalInProgress) throws VehicleNotAvailableException, VehicleInsufficientDrivingRangeException {
        this(rentalInProgress.userID,rentalInProgress.vehicleID,rentalInProgress.rentalID);
    }
}
