package com.company.banking;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import com.company.banking.handlers.ClientHandler;
import com.company.banking.handlers.AccountHandler;
import com.company.banking.handlers.OperationHandler;
import com.company.banking.models.*;
import com.company.banking.models.accounts.*;
import com.company.banking.models.operations.Operation;
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
*/      System.out.println(AccountNumber.generateAccountNumber());
        Menu.main(mainBranch);

    }

    public static class Menu {
        public static void main(Branch branch) throws IOException, ClassNotFoundException {
            Scanner stdin = new Scanner(System.in).useLocale(Locale.ENGLISH);
            readClientsFromFile(branch);

            loop:
            while(true) {
                displayMenu("Main menu", "Manage clients", "Manage client", "Exit");
                int choice = getInt(stdin);
                switch(choice) {
                    case 1:
                        manageClients(stdin, branch);
                        break;
                    case 2:
                        manageClient(stdin, branch);
                        break;
                    case 4:
                        break loop;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
        }

        private static void manageClient(Scanner stdin, Branch branch) {
            List<Client> clients = searchClientsHandler(branch, stdin);
            if(clients == null) return;
            System.out.println("Choose a client by their index:");
            int index = UserInput.getInt(stdin, 0, clients.size() - 1);
            Client client = clients.get(index);
            loop:
            while(true) {
                displayMenu(client.getName() + " " + client.getSurname(),
                        "Manage accounts", "List accounts", "Operations", "Edit client's data", "Main menu");

                int choice = getInt(stdin);
                switch(choice) {
                    case 1:
                        manageClientAccounts(stdin, client);
                        break;
                    case 2:
                        AccountHandler.listAccountsHandler(client, stdin);
                        break;
                    case 3:
                        manageClientOperations(stdin, client);
                        break;
                    case 4:
                        editClient(stdin, client);
                        break;
                    case 5:
                        break loop;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
        }

        public static void editClient(Scanner stdin, Client client) {
            loop:
            while(true) {
                displayMenu("Select client's data you want to edit:",
                        "Name", "Surname", "Address", "Return to main menu");
                int choice = getInt(stdin);
                switch (choice) {
                    case 1:
                        ClientHandler.editName(stdin, client);
                        break;
                    case 2:
                        ClientHandler.editSurname(stdin, client);
                        break;
                    case 3:
                        ClientHandler.editAddress(stdin, client);
                        break;
                    case 4:
                        break loop;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
        }

        private static void manageClientAccounts(Scanner stdin, Client client) {
            loop:
            while(true) {
                displayMenu(client.getName() + " " + client.getSurname() + " - " + "Manage accounts",
                        "Add account", "Remove account", "List all operations", "Go back");
                int choice = getInt(stdin);
                switch(choice) {
                    case 1:
                        addAccount(client, stdin);
                        break;
                    case 2:
                        AccountHandler.removeAccountHandler(client, stdin);
                        break;
                    case 3:
                        client.getAccounts().forEach(Account::printAllOperations);
                        break;
                    case 4:
                        break loop;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
        }

        public static void manageClientOperations(Scanner stdin, Client client) {
            Account account = AccountHandler.chooseFromAccounts(client, stdin);
            if(account == null) {
                System.out.println("No accounts found.");
                return;
            }
            loop:
            while(true) {
                displayMenu(client.getName() + " " + client.getSurname() + " - " + "Manage operations",
                        "Make deposit", "Make withdrawal", "Make transfer", "Go back");
                int choice = getInt(stdin);
                switch(choice) {
                    case 1:
                        OperationHandler.makeDeposit(account, stdin);
                        break;
                    case 2:
                        OperationHandler.makeWithdrawal(account, stdin);
                        break;
                    case 3:
                        OperationHandler.makeTransfer(account, stdin);
                        break;
                    case 4:
                        break loop;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
        }
        public static void addAccount(Client client, Scanner stdin) {
            loop:
            while(true) {
                displayMenu("Select account type", "Regular", "Savings", "Go back");
                int choice = getInt(stdin);
                switch(choice) {
                    case 1:
                        addRegularAccount(client);
                        break;
                    case 2:
                        addSavingsAccount(client, stdin);
                        break;
                    case 3:
                        break loop;
                }
            }
        }

        public static void manageClients(Scanner stdin, Branch branch) {
            loop:
            while(true) {
                displayMenu("Manage clients",
                        "Search clients", "Add client", "Remove client", "Main menu");
                int choice = getInt(stdin);
                switch(choice) {
                    case 1:
                        ClientHandler.searchClientsHandler(branch, stdin);
                        break;
                    case 2:
                        ClientHandler.addClientHandler(branch, stdin);
                        break;
                    case 3:
                        ClientHandler.removeClientHandler(branch, stdin);
                        break;
                    case 4:
                        break loop;
                    default:
                        System.out.println("Invalid option.");
                        break;

                }
            }
        }

        public static void displayMenu(String title, String...options) {
            System.out.println(title);
            int i = 1;
            for(String option : options) {
                System.out.println(i++ + " - " + option);
            }
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
