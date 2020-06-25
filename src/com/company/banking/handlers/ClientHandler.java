package com.company.banking.handlers;

import com.company.banking.models.*;
import com.company.banking.util.Address;

import static com.company.banking.Main.Menu.displayMenu;
import static com.company.banking.util.UserInput.*;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ClientHandler implements Serializable {
    public static void searchClientsHandler(Branch branch, Scanner stdin) {
        System.out.println("Specify clients name and surname:");
        String input = getString(stdin);
        String[] nameAndSurname = input.split(" ");
        String inputName = nameAndSurname[0];
        String inputSurname = nameAndSurname[nameAndSurname.length - 1];

        List<Client> results = branch.findClientByNameAndSurname(inputName, inputSurname);
        System.out.println("Search results: ");

        for (Client client : results) {
            System.out.println(client.toString() + "\n");
        }
    }

    public static void addClientHandler(Branch branch, Scanner stdin) {
        System.out.println("Specify new clients name:");
        String inputName = getString(stdin);
        System.out.println("Specify new clients surname:");
        String inputSurname = getString(stdin);

        System.out.println("Specify new clients country:");
        String inputCountry = getString(stdin);
        System.out.println("Specify new clients city:");
        String inputCity = getString(stdin);
        System.out.println("Specify new clients street:");
        String inputStreet = getString(stdin);
        System.out.println("Specify new clients postcode:");
        String inputPostcode = getString(stdin);
        Address address = new Address(inputCountry, inputCity, inputPostcode, inputStreet);

        try {
            branch.addNewClient(new Client(inputName, inputSurname, address));
            System.out.println("Client successfully added to current branch.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editClientHandler(Branch branch, Scanner stdin) {
        searchClientsHandler(branch, stdin);
        System.out.println("Copy and paste clients ID from the results above.");

        UUID inputId = getUUID(stdin);
        Client client = branch.findClientById(inputId);

        displayMenu("Select clients data you want to edit:",
                "Name", "Surname", "Address", "Return to main menu");

        loop:
        while(true) {
            int choice = getInt(stdin);
            switch (choice) {
                case 1:
                    editName(stdin, client);
                    break;
                case 2:
                    editSurname(stdin, client);
                    break;
                case 3:
                    editAddress(stdin, client);
                    break;
                case 4:
                    break loop;
            }
        }
    }

    public static void editName (Scanner stdin, Client client) {
        System.out.println("Specify clients new name:");
        String inputName = getString(stdin);
        client.setName(inputName);
        System.out.println("Clients name was successfully changed.");
    }

    public static void editSurname (Scanner stdin, Client client) {
        System.out.println("Specify clients new surname:");
        String inputSurname = getString(stdin);
        client.setSurname(inputSurname);
        System.out.println("Clients surname was successfully changed.");
    }

    public static void editAddress (Scanner stdin, Client client) {
        System.out.println("Specify clients new country:");
        String inputCountry = getString(stdin);
        System.out.println("Specify clients new city:");
        String inputCity = getString(stdin);
        System.out.println("Specify clients new postcode:");
        String inputPostcode = getString(stdin);
        System.out.println("Specify clients new street:");
        String inputStreet = getString(stdin);
        client.setAddress(new Address(inputCountry, inputCity, inputPostcode, inputStreet));
        System.out.println("Clients address was successfully changed.");
    }

    public static void removeClientHandler(Branch branch, Scanner stdin) {
        searchClientsHandler(branch, stdin);
        System.out.println("Copy and paste clients ID from the results above.");

        UUID inputId = getUUID(stdin);
        Client client = branch.findClientById(inputId);

        try {
            branch.removeClient(client);
            System.out.println("Client successfully removed from current branch.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
