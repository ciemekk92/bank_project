package com.company.banking.models.accounts;

import com.company.banking.models.Client;

import java.io.Serializable;

public class RegularAccount extends Account implements Serializable {
    public RegularAccount(Client owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Type: Regular" + "Account number: " + accountNumber + " Owner: " + owner.getName() + " " + owner.getSurname() +".";
    }
}
