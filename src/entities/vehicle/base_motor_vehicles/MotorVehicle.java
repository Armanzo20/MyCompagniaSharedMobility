package entities.vehicle.base_motor_vehicles;

import entities.position.Position;
import entities.vehicle.base_vehicles.Vehicle;
import enumerators.DrivingLicence;
import enumerators.PowerSource;

import javax.swing.*;
import javax.xml.stream.Location;
import java.math.BigDecimal;
import java.util.Scanner;

public abstract class MotorVehicle extends Vehicle {

    private final DrivingLicence requiredLicense;
    private final String licensePlate;
    private final PowerSource powerSource;
    private double drivingRangeInKm; //--> espressa in km che può ancora percorrere
    private final double MIN_DRIVING_RANGE_IN_KM = 50; //limite autonomia per cui è necessario il rifornimento
    private boolean insufficientDrivingRange;

    public MotorVehicle(String licensePlate, DrivingLicence requiredLicense, PowerSource powerSource, double drivingRangeInKm, Position geographicPosition,BigDecimal pricePerMinute, boolean isHelmetNecessary) {
        super(geographicPosition, isHelmetNecessary, pricePerMinute);
        this.requiredLicense = requiredLicense;
        this.licensePlate = licensePlate;
        this.powerSource = powerSource;
        this.drivingRangeInKm = drivingRangeInKm;
        if (drivingRangeInKm <= MIN_DRIVING_RANGE_IN_KM) {
            this.setAvailable(false);
            this.insufficientDrivingRange = true;
        }
    }

    public DrivingLicence getRequiredLicense() {
        return requiredLicense;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public double getDrivingRangeInKm() {
        return drivingRangeInKm;
    }

    public double getMIN_DRIVING_RANGE_IN_KM() {
        return MIN_DRIVING_RANGE_IN_KM;
    }

    public boolean isInsufficientDrivingRange() {
        return insufficientDrivingRange;
    }

    public void setDrivingRangeInKm(double drivingRangeInKm) throws Exception{
        if (drivingRangeInKm < 0) throw new IllegalArgumentException("Impossible enter negative value");
        this.drivingRangeInKm = drivingRangeInKm;
    }
}
