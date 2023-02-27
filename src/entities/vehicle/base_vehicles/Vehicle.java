package entities.vehicle.base_vehicles;
import entities.position.Position;
import jdk.javadoc.doclet.Taglet;

import javax.xml.stream.Location;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

public abstract class Vehicle {
    private final UUID vehicleID;
    private Position geographicPosition;
    private boolean isHelmetNecessary;
    private final BigDecimal pricePerMinute;
    private boolean isAvailable;

    protected Vehicle(Position geographicLocation, boolean isHelmetNecessary, BigDecimal pricePerMinute) {
        this.vehicleID = UUID.randomUUID();
        this.geographicPosition = geographicLocation;
        this.isHelmetNecessary = isHelmetNecessary;
        this.pricePerMinute = pricePerMinute;
        this.isAvailable = false;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    public BigDecimal getPricePerMinute() {
        return pricePerMinute;
    }

    public UUID getVehicleID() {
        return vehicleID;
    }


    public Position getGeographicPosition() {
        return geographicPosition;
    }

    public void setGeographicPosition(Position geographicPosition) {
        this.geographicPosition = geographicPosition;
    }

    public boolean isHelmetNecessary() {
        return isHelmetNecessary;
    }

    public boolean isAvailable() {
        return isAvailable;
    }



}
