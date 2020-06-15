package com.company.banking.models;

import com.company.banking.models.Account.Account;
import com.company.banking.util.Address;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name, surname;
    private Address address;
    private final List<Account> accounts = new ArrayList<>();

    public Client(String name, String surname, Address address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Address getAddress() {
        return address;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client ID: " + id + "\nName: " + name + "\nSurname: " + surname + "\nAddress: " + address.toString();
    }
}

