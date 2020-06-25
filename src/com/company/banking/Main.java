package com.company.banking;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import com.company.banking.handlers.ClientHandler;
import com.company.banking.handlers.AccountHandler;
import com.company.banking.models.*;
import com.company.banking.models.accounts.*;
import com.company.banking.util.*;

import com.company.banking.util.UserInput;

import static com.company.banking.handlers.ClientHandler.*;
import static com.company.banking.handlers.AccountHandler.*;
import static com.company.banking.util.UserInput.*;

/**
 * Author: Przemyslaw Reducha, Dominik Rowinski
 * Classes:
 * Branch, Account - Przemyslaw Reducha
 * Client, Transaction - Dominik Rowinski
*/
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
        Branch mainBranch = new Branch();

        /*Client c1 = new Client("Przemyslaw", "Reducha", new Address("Poland", "Bialystok", "15-215", "Mickiewicza 11"));
        Client c2 = new Client("Przemyslaw", "Reducha", new Address("Poland", "Choroszcz", "16-070", "Pilsudskiego 37"));
        Client c3 = new Client("Przemyslaw", "Reducha", new Address("Poland", "Gdansk", "71-120", "Slowackiego 5"));
        Client c4 = new Client("Donna", "Mamma", new Address("Poland", "Warszawa", "00-100", "Pulaskiego 13"));
        mainBranch.addNewClient(c1);
        mainBranch.addNewClient(c2);
        mainBranch.addNewClient(c3);
        mainBranch.addNewClient(c4);

        RegularAccount regAccount = new RegularAccount();
        regAccount.setAccountNumber("12427457658564532412412415");
        c1.addNewAccount(regAccount);*/

        //mainBranch.printAllClients();

        //System.out.println("Wyszukiwanie:");
        //List<Client> test = mainBranch.findClientByNameSurname("Przemyslaw", "Reducha");

        /*for (Client client : test) {
           System.out.println(client.toString() + "\n");
        }*/

        //saveClientsToFile(mainBranch);
        //readClientsFromFile(mainBranch);

        /*for (Client client : mainBranch.getClients()) {
            for (Account account : client.getAccounts()) {
                System.out.println(account.toString());
            }
        }
*/
        Menu.main(mainBranch);

    }

    public static class Menu {
        public static void main(Branch branch) throws IOException, ClassNotFoundException, FileNotFoundException {
            Scanner stdin = new Scanner(System.in).useLocale(Locale.ENGLISH);
            readClientsFromFile(branch);

            loop:
            while(true) {
                displayMenu("Main menu", "Manage clients", "Manage clients accounts", "Manage clients operations", "Exit");
                int choice = getInt(stdin);
                switch(choice) {
                    case 1:
                        manageClients(stdin, branch);
                        break;
                    case 2:
                        manageAccounts(stdin, branch);
                        break;
                    case 3:
                        manageOperations(stdin, branch);
                        break;
                    case 4:
                        break loop;
                }
            }
        }

        public static void manageClients(Scanner stdin, Branch branch) {
            displayMenu("Manage clients" ,
                    "Search clients", "Add client", "Edit client", "Remove client", "Main menu");
            loop:
            while(true) {
                int choice = getInt(stdin);
                switch(choice) {
                    case 1:
                        searchClientsHandler(branch, stdin);
                        break;
                    case 2:
                        addClientHandler(branch, stdin);
                        break;
                    case 3:
                        editClientHandler(branch, stdin);
                        break;
                    case 4:
                        removeClientHandler(branch, stdin);
                        break;
                    case 5:
                        break loop;
                }
            }
        }

        public static void manageAccounts(Scanner stdin, Branch branch) {
            System.out.println("Find a client to manage his accounts\n");
            System.out.println("Clients saved currently in branch: " + branch.getClients().size());
            searchClientsHandler(branch, stdin);

            UUID inputId = getUUID(stdin);
            Client client = branch.findClientById(inputId);

            displayMenu("Manage accounts", "List all accounts of selected client", "Add new account", "Edit account", "Remove account", "Main menu");

            loop:
            while(true) {
                int choice = getInt(stdin);
                switch(choice) {
                    case 1:
                        listAccountsHandler(client, stdin);
                        break;
                    case 2:
                        addAccountHandler(client, stdin);
                        break;
                    case 3:
                        editAccountHandler(client, stdin);
                        break;
                    case 4:
                        removeAccountHandler(client, stdin);
                        break;
                    case 5:
                        break loop;
                }
            }
        }

        public static void manageOperations(Scanner stdin, Branch branch) {

        }

        public static void displayMenu(String title, String...options) {
            System.out.println(title);
            int i = 1;
            for(String option : options) {
                System.out.println(i++ + " - " + option);
            }
            System.out.print("> ");
        }
    }

    private static void saveClientsToFile(Branch branch) throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("clients.dat"));
        for (Client client : branch.getClients()) {
            output.writeObject(client);
            System.out.println("Client " + client.getName() + " " + client.getSurname() + " was saved to file.");
        }
        output.close();
    }

    private static void readClientsFromFile(Branch branch) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("clients.dat"));
        try {
            while (true) {
                Client client = (Client) input.readObject();
                branch.addNewClient(client);
                System.out.println("Client " + client.getName() + " " + client.getSurname() + " was read from file.");
            }
        } catch (EOFException exception) {
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        input.close();
    }
}
