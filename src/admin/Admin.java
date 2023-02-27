package admin;

import Database.Database;
import entities.position.Position;
import entities.vehicle.base_motor_vehicles.MotorVehicle;
import entities.vehicle.base_vehicles.Vehicle;

import java.util.Scanner;
import java.util.UUID;

public class Admin {

    public static void updateStatusVehicle(UUID vehicleID) {
        Vehicle vehicle = Database.getUuidVehiclehashMap().get(vehicleID);
        Scanner s = new Scanner(System.in);
        if (vehicle instanceof MotorVehicle) {
            updateDistanceDriving(vehicle);
        }
        updatePosition(vehicle);
    }

    private static void updatePosition(Vehicle vehicle) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter longitude [" + Position.getLongitudeMin() + ", " + Position.getLongitudeMax() + "]: ");
        try {
            vehicle.getGeographicPosition().setLongitude(s.nextDouble());
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.print("Enter Latitude [" + Position.getLatitudeMin() + ", " + Position.getLatitudeMax() + "]: ");
        try {
            vehicle.getGeographicPosition().setLatitude(s.nextDouble());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void updateDistanceDriving(Vehicle vehicle) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the new value of the driving distance in Km: ");
        try {
            ((MotorVehicle) vehicle).setDrivingRangeInKm(s.nextDouble());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
