package com.company.banking.handlers;

import com.company.banking.models.*;
import com.company.banking.util.Address;
import com.company.banking.util.UserInput;

import static com.company.banking.util.UserInput.*;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class ClientHandler implements Serializable {
    public static List<Client> searchClientsHandler(Branch branch, Scanner stdin) {
        System.out.println("Specify client's name and surname:");
        String input = getString(stdin);
        String[] nameAndSurname = input.split(" ");
        String inputName = nameAndSurname[0];
        String inputSurname = nameAndSurname[nameAndSurname.length - 1];

        List<Client> results = branch.findClientByNameAndSurname(inputName, inputSurname);

        if(results.size() == 0) {
            System.out.println("No results found.");
            return null;
        }

        System.out.println("Search results:");

        for (Client client : results) {
            System.out.println("[" + results.indexOf(client) + "]\n" + client.toString() + "\n");
        }

        return results;
    }

    public static void addClientHandler(Branch branch, Scanner stdin) {
        System.out.println("Specify new client's name:");
        String inputName = getString(stdin);
        System.out.println("Specify new client's surname:");
        String inputSurname = getString(stdin);
        System.out.println("Specify new client's country:");
        String inputCountry = getString(stdin);
        System.out.println("Specify new client's city:");
        String inputCity = getString(stdin);
        System.out.println("Specify new client's street:");
        String inputStreet = getString(stdin);
        System.out.println("Specify new client's postcode:");
        String inputPostcode = getString(stdin);
        Address address = new Address(inputCountry, inputCity, inputPostcode, inputStreet);

        try {
            branch.addNewClient(new Client(inputName, inputSurname, address));
            System.out.println("Client successfully added.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    public static void editName (Scanner stdin, Client client) {
        System.out.println("Specify clients new name:");
        String inputName = getString(stdin);
        client.setName(inputName);
        System.out.println("Client's name was successfully changed.");
    }

    public static void editSurname (Scanner stdin, Client client) {
        System.out.println("Specify clients new surname:");
        String inputSurname = getString(stdin);
        client.setSurname(inputSurname);
        System.out.println("Client's surname was successfully changed.");
    }

    public static void editAddress (Scanner stdin, Client client) {
        System.out.println("Specify client's new country:");
        String inputCountry = getString(stdin);
        System.out.println("Specify client's new city:");
        String inputCity = getString(stdin);
        System.out.println("Specify client's new postcode:");
        String inputPostcode = getString(stdin);
        System.out.println("Specify client's new street:");
        String inputStreet = getString(stdin);
        client.setAddress(new Address(inputCountry, inputCity, inputPostcode, inputStreet));
        System.out.println("Client's address was successfully changed.");
    }

    public static void removeClientHandler(Branch branch, Scanner stdin) {
        List<Client> clients = searchClientsHandler(branch, stdin);
        if(clients == null) return;
        System.out.println("Choose a client by their index. -1 to cancel.");
        displayInputIndicator();
        int index = UserInput.getInt(stdin, -1, clients.size() - 1);
        if(index == -1) return;

        Client client = clients.get(index);

        try {
            branch.removeClient(client);
            System.out.println("Client successfully removed.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
}
