package entities.user;

import entities.position.Position;
import enumerators.DrivingLicence;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;

public class User {

    //Nome \
    //Cognome \
    //Data di nascita \
    //Codice fiscale \
    //
    //Casco \
    //Patenti \
    //credito
    private UUID userID;
    private String name;
    private String username;
    private String fiscalCode;
    private Position userPosition;
    private LocalDate dateOfBirth;
    private boolean hasHelmet;
    private double userCredit;
    private HashSet<DrivingLicence> drivingLicencehashSet;

    public User(String name, String username, String fiscalCode, LocalDate dateOfBirth,boolean hasHelmet) {
        this.name = name;
        this.username = username;
        this.fiscalCode = fiscalCode;
        this.dateOfBirth = dateOfBirth;
        this.hasHelmet = hasHelmet;
        this.userCredit = 0;
        this.drivingLicencehashSet = new HashSet<>();
    }

    public UUID getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isHasHelmet() {
        return hasHelmet;
    }

    public double getUserCredit() {
        return userCredit;
    }

    public HashSet<DrivingLicence> getDrivingLicencehashSet() {
        return drivingLicencehashSet;
    }
}
