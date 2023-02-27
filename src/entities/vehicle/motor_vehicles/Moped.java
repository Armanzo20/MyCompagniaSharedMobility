package entities.vehicle.motor_vehicles;

import entities.position.Position;
import entities.vehicle.base_motor_vehicles.MotorVehicle;

import javax.xml.stream.Location;
import java.math.BigDecimal;

public class Moped extends MotorVehicle {

    private final String name;
    public Moped(Position geographicPosition, BigDecimal price) {
        super(geographicPosition, false, price);
        this.name = "Electric Scooter";
    }
}
