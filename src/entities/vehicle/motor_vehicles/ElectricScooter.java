package entities.vehicle.motor_vehicles;

import entities.position.Position;
import entities.vehicle.base_motor_vehicles.MotorVehicle;
import enumerators.DrivingLicence;
import enumerators.PowerSource;

import javax.xml.stream.Location;
import java.math.BigDecimal;

public class ElectricScooter extends MotorVehicle {

    private final String name;
    public ElectricScooter(String licensePlate, DrivingLicence requiredLicense, PowerSource powerSource, double drivingRangeInKm, Position geographicPosition, BigDecimal pricePerMinute, boolean isHelmetNecessary) {
        super(licensePlate,requiredLicense,powerSource,drivingRangeInKm,geographicPosition,pricePerMinute,isHelmetNecessary);
        this.name = "Electric Scooter";
    }
}
