package Database;

import entities.rental.RentalInProgress;
import entities.rental.terminated_rental.TerminatedRental;
import entities.user.User;
import entities.vehicle.base_vehicles.Vehicle;
import enumerators.DrivingLicence;

import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.UUID;

public class Database {

    private static HashMap<UUID, Vehicle> uuidVehiclehashMap;
    private static HashMap<UUID, User> uuidUserHashMap;
    private static HashMap<UUID, RentalInProgress> uuidRentalInProgresslHashMap;
    private static HashMap<UUID, TerminatedRental> uuidTerminatedRentalHashMap;

    private static HashMap<UUID, HashSet<DrivingLicence>> uuidUserHashSetUserDrivingLicensesHashMap;

    public static HashMap<UUID, Vehicle> getUuidVehiclehashMap() {
        return uuidVehiclehashMap;
    }

    public static HashMap<UUID, User> getUuidUserHashMap() {
        return uuidUserHashMap;
    }

    public static HashMap<UUID, RentalInProgress> getUuidRentalInProgresslHashMap() {
        return uuidRentalInProgresslHashMap;
    }

    public static HashMap<UUID, TerminatedRental> getUuidTerminatedRentalHashMap() {
        return uuidTerminatedRentalHashMap;
    }


    public static void addUser() {

    }
    public static RentalInProgress removeRentalInProgressByRentalID(UUID rentalID) throws Exception {
        if (!uuidRentalInProgresslHashMap.containsKey(rentalID)) {
            throw new NoSuchElementException("Rental not found, impossible to terminate");
        }
        return uuidRentalInProgresslHashMap.remove(rentalID);
    }

    public static void addUserDrivingLicenses(UUID userID,DrivingLicence drivingLicence) {
        HashSet<DrivingLicence> drivingLicences;
        if (uuidUserHashSetUserDrivingLicensesHashMap.containsKey(userID)) {
            drivingLicences = uuidUserHashSetUserDrivingLicensesHashMap.get(userID);
            if (drivingLicences.contains(drivingLicence)) {
                throw new IllegalArgumentException("The license " + drivingLicence + " is already included among the user's licenses");
            }
        } else {
            drivingLicences = new HashSet<DrivingLicence>();
        }
        drivingLicences.add(drivingLicence);
    }



    /*

    public static Vehicle getVehicleByID(UUID vehicleID) {
       if (!uuidVehiclehashMap.containsKey(vehicleID)) {
           throw new NoSuchElementException("VehicleID not found within the map");
       }
       return uuidVehiclehashMap.get(vehicleID);
    }
*/

    public static User removeUserByID(UUID userID) throws NoSuchElementException {
        if (!uuidUserHashMap.containsKey(userID)) {
            throw new NoSuchElementException("UserID not found within the map");
        }
        return uuidUserHashMap.remove(userID);
    }
/*
    public static RentalInProgress getRentalByID(UUID rentalID) {
        if (!uuidRentalInProgresslHashMap.containsKey(rentalID)) {
            throw new NoSuchElementException("RentalID not found within the map");
        }
        return uuidRentalInProgresslHashMap.get(rentalID);
    }

    public static TerminatedRental getTerminateRentalByID(UUID rentalID) {
        if (!uuidTerminatedRentalHashMap.containsKey(rentalID)) {
            throw new NoSuchElementException("RentalID not found within the map");
        }
        return uuidTerminatedRentalHashMap.get(rentalID);
    }

     */
}
