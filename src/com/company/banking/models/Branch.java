package com.company.banking.models;

import java.util.*;
import java.util.stream.Collectors;

public class Branch {
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
        clients.add(client);
    }

    public void removeClient (Client client) {
        clients.remove(client);
    }

    public List<Client> findClientByNameSurname (String name, String surname) {
        return clients.stream()
                .filter(client -> name.equals(client.getName()))
                .filter(client -> surname.equals(client.getSurname()))
                .collect(Collectors.toList());
    }

    public void printAllClients () {
        for (Client client : clients) {
            System.out.println(client.toString() + "\n");
        }
    }
}
