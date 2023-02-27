package entities.rental;

import Database.Database;
import MyException.VehicleInsufficientDrivingRangeException;
import MyException.VehicleNotAvailableException;
import entities.position.Position;
import entities.vehicle.base_motor_vehicles.MotorVehicle;
import entities.vehicle.base_vehicles.Vehicle;

import java.time.LocalDateTime;
import java.util.UUID;


//secondo c'è bidogno di una classe noleggio e poi di due funzioni di sistama inizio corsa che inizializa
// un istanza di noleggio e la mette nel database
//poi un altra funzione fine corsa che sato id del noleggio termine il noleggio e aggiorna i valori sul prezzo e i km percorsi.
public class RentalInProgress {

    //registra inizio noleggio con un TimeStamp;
    //registra fine con TimeStamp;
    //inserisco km percorsi
    //aggiorno autonomia auto
    //calcola prezzo veicolo
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
