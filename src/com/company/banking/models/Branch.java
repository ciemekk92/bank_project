package com.company.banking.models;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Branch implements Serializable {
    private UUID id;
    private final List<Client> clients = new ArrayList<>();

    public Branch() {}

    public UUID getId () {
        return id;
    }

    public void setId (UUID id) {
        this.id = id;
    }

    public List<Client> getClients () {
        return Collections.unmodifiableList(clients);
    }

    public void addNewClient (Client client) {
        client.setBranch(this);
        clients.add(client);
    }

    public void removeClient (Client client) {
        client.setBranch(null);
        clients.remove(client);
    }

    public List<Client> findClientByNameAndSurname (String name, String surname) {
        return clients.stream()
                .filter(client -> name.equalsIgnoreCase(client.getName()))
                .filter(client -> surname.equalsIgnoreCase(client.getSurname()))
                .collect(Collectors.toList());
    }

    public Client findClientById (UUID id) {
        return clients.stream()
                .filter(client -> id.equals(client.getId()))
                .findAny()
                .orElse(null);
    }

    public void printAllClients () {
        for (Client client : clients) {
            System.out.println(client.toString() + "\n");
        }
    }
}
