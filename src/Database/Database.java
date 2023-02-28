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

    private static Database database;

    public static Database getDatabase() {
        if (database != null) {
            return database;
        }
        return new Database();
    }

    private HashMap<UUID, Vehicle> uuidVehiclehashMap;
    private HashMap<UUID, User> uuidUserHashMap;
    private HashMap<UUID, RentalInProgress> uuidRentalInProgresslHashMap;
    private HashMap<UUID, TerminatedRental> uuidTerminatedRentalHashMap;

    private HashMap<UUID, HashSet<DrivingLicence>> uuidUserHashSetUserDrivingLicensesHashMap;

    public HashMap<UUID, Vehicle> getUuidVehiclehashMap() {
        return uuidVehiclehashMap;
    }

    public HashMap<UUID, User> getUuidUserHashMap() {
        return uuidUserHashMap;
    }

    public HashMap<UUID, RentalInProgress> getUuidRentalInProgresslHashMap() {
        return uuidRentalInProgresslHashMap;
    }

    public HashMap<UUID, TerminatedRental> getUuidTerminatedRentalHashMap() {
        return uuidTerminatedRentalHashMap;
    }


    public void addNewUser(User user) {
        if (uuidUserHashMap.containsKey(user.getUserID())) {
            throw new IllegalArgumentException("The User " + user.getUserID() + " is already present");
        }
        uuidUserHashMap.put(user.getUserID(), user);

    }

    public void removeUser(UUID userID) {
        if (!uuidUserHashMap.containsKey(userID)) {
            throw new IllegalArgumentException("The User " + userID + " is not present");
        }
        uuidUserHashMap.remove(userID);
        uuidUserHashSetUserDrivingLicensesHashMap.remove(userID); //elimina le patenti associate all'utente se esistono
    }

    public void addTerminatedRental(TerminatedRental terminatedRental) {
        if (uuidTerminatedRentalHashMap.containsKey(terminatedRental.getRentalID())) {
            throw new IllegalArgumentException("The Rental " + terminatedRental.getRentalID() + " is already present");
        }
        uuidTerminatedRentalHashMap.put(terminatedRental.getRentalID(), terminatedRental);
    }

    public void removeTerminateRental(UUID rentalID) {
        if (!uuidTerminatedRentalHashMap.containsKey(rentalID)) {
            throw new NoSuchElementException("Rental not found, impossible to remove");
        }
        uuidTerminatedRentalHashMap.remove(rentalID);
    }


    public void addRentalRentalInProgress(RentalInProgress rentalInProgress) {
        if (uuidRentalInProgresslHashMap.containsKey(rentalInProgress.getRentalID())) {
            throw new IllegalArgumentException("The Rental " + rentalInProgress.getRentalID() + " is already present");
        }
        uuidRentalInProgresslHashMap.put(rentalInProgress.getRentalID(), rentalInProgress);

    }


    public RentalInProgress removeRentalInProgress(UUID rentalID) {
        if (!uuidRentalInProgresslHashMap.containsKey(rentalID)) {
            throw new NoSuchElementException("Rental not found, impossible to terminate");
        }

        return uuidRentalInProgresslHashMap.remove(rentalID);
    }

    public void addUserDrivingLicenses(UUID userID, DrivingLicence drivingLicence) {
        HashSet<DrivingLicence> drivingLicences;
        if (uuidUserHashSetUserDrivingLicensesHashMap.containsKey(userID)) {
            drivingLicences = uuidUserHashSetUserDrivingLicensesHashMap.get(userID);
            if (drivingLicences.contains(drivingLicence)) {
                throw new IllegalArgumentException("The license " + drivingLicence + " is already included among the user's licenses");
            }
        } else {
            drivingLicences = new HashSet<>();
        }
        drivingLicences.add(drivingLicence);
    }


    public User getUserByID(UUID userID) {
        if (!uuidUserHashMap.containsKey(userID)) {
            throw new NoSuchElementException("userID not found within the map");
        }
        return uuidUserHashMap.get(userID);
    }

    public Vehicle getVehicleByID(UUID vehicleID) {
        if (!uuidVehiclehashMap.containsKey(vehicleID)) {
            throw new NoSuchElementException("VehicleID not found within the map");
        }
        return uuidVehiclehashMap.get(vehicleID);
    }
    public TerminatedRental getTerminateRentalByID(UUID rentalID) {
        if (!uuidTerminatedRentalHashMap.containsKey(rentalID)) {
            throw new NoSuchElementException("RentalID not found within the map");
        }
        return uuidTerminatedRentalHashMap.get(rentalID);
    }

    public RentalInProgress getRentalInProgressByID(UUID rentalID) {
        if (!uuidRentalInProgresslHashMap.containsKey(rentalID)) {
            throw new NoSuchElementException("RentalID not found within the map");
        }
        return uuidRentalInProgresslHashMap.get(rentalID);
    }

}
