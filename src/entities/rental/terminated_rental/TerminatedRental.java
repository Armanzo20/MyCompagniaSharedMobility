package entities.rental.terminated_rental;

import Database.Database;
import MyException.InsufficientRentalMinutesException;
import MyException.VehicleInsufficientDrivingRangeException;
import MyException.VehicleNotAvailableException;
import admin.AdminSingleton;
import entities.position.Position;
import entities.rental.RentalInProgress;
import entities.vehicle.base_vehicles.Vehicle;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class TerminatedRental extends RentalInProgress {
    private LocalDateTime rentalEndTime;
    private Position rentalEndPosition;
    private BigDecimal totPrice;
    private static final Duration MIN_DURATION_IN_MINUTES = Duration.ofMinutes(5);

    //crea un oggetto TerminateRental a partire da ID di RentalInProgress
    //rimuove dal database il noleggio che era in corso,
    //aggiunge al database il noleggio terminato
    public TerminatedRental(LocalDateTime rentalEndTime, UUID rentalID) {
        //creo l'oggetto termina noleggio a partire dal noleggio in corso;
        super(Database.getDatabase().getRentalInProgressByID(rentalID));
        //recupero il veicolo noleggiato
        Vehicle vehicle = Database.getDatabase().getVehicleByID(this.vehicleID);
        this.rentalEndTime = rentalEndTime;

        //verifico se la durata è maggiore o uguale della durata minima
        checkMinDuration();

        AdminSingleton.getInstance().updateStatusVehicle(vehicleID); //aggiorno lo stato dle veicolo posizione + autonomia se presnete
        this.rentalEndPosition = vehicle.getGeographicPosition();
        this.totPrice.setScale(2);
        calculateTotPrice(vehicle.getPricePerMinute());
        vehicle.setAvailable(true);
        Database.getDatabase().addTerminatedRental(this);
        AdminSingleton.getInstance().transactionManager(this.userID,this.rentalID);
    }

    public LocalDateTime getRentalEndTime() {
        return rentalEndTime;
    }

    public Position getRentalEndPosition() {
        return rentalEndPosition;
    }

    public BigDecimal getTotPrice() {
        return totPrice;
    }

    //calcola il tempo del noleggio
    private Duration getRentalDuration() {
        return Duration.between(this.rentalStartTime, this.rentalEndTime);
    }

    //calcola il prezzo totale della corsa base al tempo della corsa e alla tariffa la minuto
    private void calculateTotPrice(BigDecimal pricePerMinute) {
        this.totPrice = pricePerMinute.multiply(BigDecimal.valueOf(getRentalDuration().toMinutes()));
    }


    //verifica se i minuti del noleggio sono maggiori o uguali del limite pre impostato
    private void checkMinDuration(){
        if (getRentalDuration().toMinutes() >= MIN_DURATION_IN_MINUTES.toMinutes()) {
            throw new InsufficientRentalMinutesException("Impossible to terminate rental" +
                    " because it has not reached the minimum rental time of " + MIN_DURATION_IN_MINUTES +
                    " minutes. You have " + (getRentalDuration().toMinutes()-MIN_DURATION_IN_MINUTES.toMinutes())  + " minutes left");
        }
    }
}
