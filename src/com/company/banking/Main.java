package com.company.banking;

import java.io.*;
import java.util.*;
import com.company.banking.models.*;
import com.company.banking.models.accounts.*;
import com.company.banking.util.*;

/**
 * Author: Przemyslaw Reducha, Dominik Rowinski
 * Classes:
 * Branch, Account - Przemyslaw Reducha
 * Client, Transaction - Dominik Rowinski
*/
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
        Branch mainBranch = new Branch();

        Client c1 = new Client("Przemyslaw", "Reducha", new Address("Poland", "Bialystok", "15-215", "Mickiewicza 11"));
        Client c2 = new Client("Przemyslaw", "Reducha", new Address("Poland", "Choroszcz", "16-070", "Pilsudskiego 37"));
        Client c3 = new Client("Przemyslaw", "Reducha", new Address("Poland", "Gdansk", "71-120", "Slowackiego 5"));
        Client c4 = new Client("Donna", "Mamma", new Address("Poland", "Warszawa", "00-100", "Pulaskiego 13"));
        mainBranch.addNewClient(c1);
        mainBranch.addNewClient(c2);
        mainBranch.addNewClient(c3);
        mainBranch.addNewClient(c4);

        RegularAccount regAccount = new RegularAccount();
        regAccount.setAccountNumber("12427457658564532412412415");
        c1.addNewAccount(regAccount);

        //mainBranch.printAllClients();

        //System.out.println("Wyszukiwanie:");
        //List<Client> test = mainBranch.findClientByNameSurname("Przemyslaw", "Reducha");

        /*for (Client client : test) {
           System.out.println(client.toString() + "\n");
        }*/

        saveClientsToFile(mainBranch);
        //readClientsFromFile(mainBranch);

        for (Client client : mainBranch.getClients()) {
            for (Account account : client.getAccounts()) {
                System.out.println(account.toString());
            }
        }

    }

    private static void saveClientsToFile(Branch branch) throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("clients.dat"));
        for (Client client : branch.getClients()) {
            output.writeObject(client);
            System.out.println("Klient " + client.getName() + " " + client.getSurname() + " został zapisany do pliku.");
        }
        output.close();
    }

    private static void readClientsFromFile(Branch branch) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("clients.dat"));
        try {
            while (true) {
                Client client = (Client) input.readObject();
                branch.addNewClient(client);
                System.out.println("Klient " + client.getName() + " " + client.getSurname() + " został odczytany z pliku.");
            }
        } catch (EOFException exception) {
        } catch(IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        input.close();
    }
}
