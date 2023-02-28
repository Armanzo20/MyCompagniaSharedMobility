package entities.user;

import Database.Database;
import entities.position.Position;
import enumerators.DrivingLicence;

import java.math.BigDecimal;
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
    private final UUID userID;
    private final String name;
    private final String username;
    private final String fiscalCode;
    private Position userPosition;
    private final LocalDate dateOfBirth;
    private boolean hasHelmet;
    private BigDecimal userCredit;
    private final BigDecimal CREDIT_DEFAULT;


    public User(UserBuilder userBuilder) {
        this.userID = UUID.randomUUID();
        this.name = userBuilder.getName();
        this.username = userBuilder.getUsername();
        this.fiscalCode = userBuilder.getFiscalCode();
        this.dateOfBirth = userBuilder.getDateOfBirth();
        this.hasHelmet = false;
        this.CREDIT_DEFAULT = BigDecimal.valueOf(0);
        this.userCredit.setScale(2);
        this.userCredit = CREDIT_DEFAULT;
    }

    //cosa può fare un utente:

    /*
    - noleggiare una vettura
    - ottenere le vetture libere all'interno di un rage dato rispetto alla posizione dell'utente
    - terminare il noleggio
    - ottenere tutte le proprie corse e relativo storico di transizioni
    - poter settare la voce ho il casco non ho il casco
    - attivare la localizzazione e inserire la propria posizione
    - visualizzare le tariffe d tutti i mezzi che mette a disposizione l'azienda
     */

    public void setUserPosition(Position userPosition) {
        this.userPosition = userPosition;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    public void setUserCredit(BigDecimal userCredit) {
        this.userCredit = userCredit;
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

    public BigDecimal getUserCredit() {
        return userCredit;
    }



    //aggiunge una patente alla lista delle patenti dell'utente;
    public void addNewDrivingLicence(DrivingLicence drivingLicence) {
        Database.getDatabase().addUserDrivingLicenses(this.userID,drivingLicence);
    }

    //rimuove una patente dalla lista delle patenti dell'utente...

}
