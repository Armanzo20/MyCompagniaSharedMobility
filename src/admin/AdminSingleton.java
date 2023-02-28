package admin;

import Database.Database;
import entities.position.Position;
import entities.rental.terminated_rental.TerminatedRental;
import entities.user.User;
import entities.user.UserBuilder;
import entities.vehicle.base_motor_vehicles.MotorVehicle;
import entities.vehicle.base_vehicles.Vehicle;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;


public class AdminSingleton {

    private static AdminSingleton Instance;

    private AdminSingleton() {

    }

    public static AdminSingleton getInstance() {
        if (Instance == null) {
            Instance = new AdminSingleton();
        }
        return Instance;
    }

    //funzioni di sistema:
    /*

    - avere una lista di tutti i noleggi dell'utente
    - aggiungere un nuovo veicolo
    - rimuovere un veicolo
    - rifornire un veicolo
    - aggiornare stato veicolo

    - avere una lista dei veicoli in uso con relative posizioni e parametri
    - avere una lista di tutti i veicoli disponibili
    - poter modificare tutte le costanti come il prezzo di un noleggio e l'autonomia minima
    */


    public boolean setBooleanByScanner(boolean booleanToModify, String attributeName) {
        Scanner s = new Scanner(System.in);
        String booleanString = "";
        while (true) {
            if (!setStringValueByScanner(booleanString, attributeName)) return false;
            try {
                if (Boolean.parseBoolean(booleanString)) {
                    booleanToModify = true;
                } else {
                    booleanToModify = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                System.out.println("Please insert a valid value or type /back to quit.");
                String command = s.next();
                if (command.equalsIgnoreCase("/back")) {
                    return false;
                }
            }
        }
    }

    public boolean setLocalDateValueByScanner(LocalDate localDateToModify, String attributeName) {
        Scanner s = new Scanner(System.in);
        String localDateString = "";
        if (!setStringValueByScanner(localDateString, attributeName)) return false;

        while (true) {
            try {
                localDateToModify = LocalDate.parse(localDateString);
                return true;
            } catch (DateTimeException e) {
                System.out.println(e);
                System.out.println("Please insert a valid date or type /back to quit.");
                String command = s.next();
                if (command.equalsIgnoreCase("/back")) {
                    return false;
                }
            }
        }
    }

    //andrebbe impostato anche un numero minimo di tentativi
    public boolean setStringValueByScanner(String stringToModify, String attributeName) {
        Scanner s = new Scanner(System.in);
        System.out.print("Insert " + attributeName + ": ");
        while (true) {
            try {
                stringToModify = s.next();
                return true;
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Please insert a valid value or type /back to quit.");
                String command = s.next();
                if (command.equalsIgnoreCase("/back")) {
                    return false;
                }
            }
        }
    }

    public void updateStatusVehicle(UUID vehicleID) {
        Vehicle vehicle = Database.getDatabase().getVehicleByID(vehicleID);;
        Scanner s = new Scanner(System.in);
        if (vehicle instanceof MotorVehicle) {
            updateDistanceDriving(vehicle);
        }
        updateVehiclePosition(vehicle);
    }

    private void updateVehiclePosition(Vehicle vehicle) {
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

    private void updateDistanceDriving(Vehicle vehicle) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the new value of the driving distance in Km: ");
        try {
            ((MotorVehicle) vehicle).setDrivingRangeInKm(s.nextDouble());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //- registrare un nuovo utente
    public void addNewUser() {
        UserBuilder userBuilder = new UserBuilder();
        User user = null;
        try {
            user = userBuilder.build();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        Database.getDatabase().addNewUser(user);
    }


    //rimuove un utente passato come parametro
    public void removeUser(UUID userID) {
        try {
          Database.getDatabase().removeUser(userID);
        } catch (Exception e) {
            System.out.println(e);
        }
          System.out.println("User " + userID + " successfully removed");
    }

    //- controllare il credito e gestire la transazione
    public void transactionManager(UUID userID,UUID rentalID){
        User user = Database.getDatabase().getUserByID(userID);
        TerminatedRental terminatedRental = Database.getDatabase().getTerminateRentalByID(rentalID);
        user.setUserCredit(decrementCreditUser(user.getUserCredit(),terminatedRental.getTotPrice()));
    }

    private BigDecimal decrementCreditUser(BigDecimal creditUser,BigDecimal totPriceRental) {
        return creditUser.subtract(totPriceRental);
    }


}
