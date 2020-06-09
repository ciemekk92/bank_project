package com.company.banking.models;

// exceptions

public class Client {
    private String name, surname, clientId;
    private Account[] accounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getClientId() {
        return clientId;
    }

    public void setId(String id) {
        this.clientId = id;
    }

    public Client (String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}

