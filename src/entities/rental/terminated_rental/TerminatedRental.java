package entities.rental.terminated_rental;

import Database.Database;
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

    //crea un oggetto TerminateRental a partire da ID di RentalInProgress
    //rimuove dal database il noleggio che era in corso appena terminato,
    //aggiunge al database il noleggio terminato
    public TerminatedRental(LocalDateTime rentalEndTime, UUID rentalID) throws Exception {
        super(Database.removeRentalInProgressByRentalID(rentalID));
        Vehicle vehicle = Database.getUuidVehiclehashMap().get(this.vehicleID);
        this.rentalEndTime = rentalEndTime;
        AdminSingleton.updateStatusVehicle(vehicleID); //aggiorno lo stato dle veicolo posizione + autonomia se presnete
        this.rentalEndPosition = vehicle.getGeographicPosition();
        calculateTotPrice(vehicle.getPricePerMinute());
        vehicle.setAvailable(true);
        Database.getUuidTerminatedRentalHashMap().put(this.vehicleID, this);
    }


    //calcola il tempo del noleggio
    private Duration getRentalDuration() {
        return Duration.between(this.rentalStartTime, this.rentalEndTime);
    }

    //calcola il prezzo totale della corsa base al tempo della corsa e alla tariffa la minuto
    private void calculateTotPrice(BigDecimal pricePerMinute) {
        this.totPrice = pricePerMinute.multiply(BigDecimal.valueOf(getRentalDuration().toMinutes()));
    }


}
