package entities.vehicle.human_powered_vehicle;

import entities.position.Position;
import entities.vehicle.base_vehicles.Vehicle;

import javax.xml.stream.Location;
import java.math.BigDecimal;

public class Bicycle extends Vehicle {

    private final String name;

    protected Bicycle(Position geographicPosition, BigDecimal price) {
        super(geographicPosition,true, price);
        this.name = "Bicycle";
    }
}
