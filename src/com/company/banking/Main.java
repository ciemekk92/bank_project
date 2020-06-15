package com.company.banking;

import java.util.*;
import com.company.banking.models.*;
import com.company.banking.util.Address;

/**
 * Author: Przemyslaw Reducha, Dominik Rowinski
 * Classes:
 * Branch, Account - Przemyslaw Reducha
 * Client, Transaction - Dominik Rowinski
*/
public class Main {

    public static void main(String[] args) {
        Branch mainBranch = new Branch();

        mainBranch.addNewClient(new Client("Przemyslaw", "Reducha", new Address("Poland", "Bialystok", "15-215", "Mickiewicza 11")));
        mainBranch.addNewClient(new Client("Przemyslaw", "Reducha", new Address("Poland", "Choroszcz", "16-070", "Pilsudskiego 37")));
        mainBranch.addNewClient(new Client("Przemyslaw", "Reducha", new Address("Poland", "Gdansk", "71-120", "Slowackiego 5")));
        mainBranch.addNewClient(new Client("Donna", "Mamma", new Address("Poland", "Warszawa", "00-100", "Pulaskiego 13")));
        mainBranch.printAllClients();

        System.out.println("Wyszukiwanie:");
        List<Client> test = mainBranch.findClientByNameSurname("Przemyslaw", "Reducha");

        for (Client client : test) {
            System.out.println(client.toString() + "\n");
        }
    }
}
