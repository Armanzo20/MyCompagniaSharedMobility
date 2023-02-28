package entities.user;

import admin.AdminSingleton;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserBuilder {

    private String name;
    private String username;
    private String fiscalCode;
    private LocalDate dateOfBirth;

    public UserBuilder() {
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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


    public boolean setName() {
        if (AdminSingleton.getInstance().setStringValueByScanner(this.name, "name")) return true;
        return false;
    }


    public boolean setUsername() {
        if (AdminSingleton.getInstance().setStringValueByScanner(this.username, "username")) return true;
        return false;
    }

    public boolean setFiscalCode() {
        if (AdminSingleton.getInstance().setStringValueByScanner(this.fiscalCode, "fiscal code")) return true;
        return false;
    }

    public boolean setDateOfBirth() {
        if (AdminSingleton.getInstance().setLocalDateValueByScanner(this.dateOfBirth,"date of birth (YYYY-MM-DD")) return true;
        return false;
    }


    public User build() {
        if (!setName() || !setUsername() || !setFiscalCode() || !setDateOfBirth()) {
            throw new IllegalArgumentException("Impossible to create a new User because the data is incomplete");
        }
        return new User(this);
    }
}
