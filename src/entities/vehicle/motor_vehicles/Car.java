package entities.vehicle.motor_vehicles;

import entities.position.Position;
import entities.vehicle.base_motor_vehicles.MotorVehicle;
import enumerators.DrivingLicence;
import enumerators.PowerSource;

import javax.xml.stream.Location;
import java.math.BigDecimal;

public class Car extends MotorVehicle {

    private final String name;
    public Car(String licensePlate, DrivingLicence requiredLicense, PowerSource powerSource, double drivingRangeInKm, Position geographicPosition, BigDecimal pricePerMinute, boolean isHelmetNecessary) {
        super(licensePlate,requiredLicense,powerSource,drivingRangeInKm,geographicPosition,pricePerMinute,isHelmetNecessary);
        this.name = "Car";
    }

}
