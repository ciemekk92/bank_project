package com.company.banking;

import java.io.*;
import java.util.*;

import com.company.banking.handlers.ClientHandler;
import com.company.banking.handlers.AccountHandler;
import com.company.banking.handlers.OperationHandler;
import com.company.banking.models.*;
import com.company.banking.models.accounts.*;
import com.company.banking.util.*;

import static com.company.banking.handlers.ClientHandler.*;
import static com.company.banking.handlers.AccountHandler.*;
import static com.company.banking.util.UserInput.*;

/**
 * Author: Przemyslaw Reducha, Dominik Rowinski
 *
 * Classes:
 * Branch, Account, subclasses - Przemyslaw Reducha
 * Client, Transaction, subclasses - Dominik Rowinski
 * AccountHandler, ClientHandler - Przemyslaw Reducha
 * OperationHandler, Address - Dominik Rowinski
 * Remaining classes - together
 *
*/

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, FileNotFoundException{
        Branch mainBranch = new Branch();
        Menu.main(mainBranch);
    }

    public static class Menu {
        public static void main(Branch branch) throws IOException, ClassNotFoundException {
            Scanner stdin = new Scanner(System.in).useLocale(Locale.ENGLISH);
            //readClientsFromFile(branch);

            loop:
            while(true) {
                displayMenu("Main menu", "Manage clients", "Manage client", "Read clients from file (clients.dat)", "Save clients to file", "Exit");
                int choice = getInt(stdin);
                switch(choice) {
                    case 1:
                        manageClients(stdin, branch);
                        break;
                    case 2:
                        manageClient(stdin, branch);
                        break;
                    case 3:
                        readClientsFromFile(branch);
                        break;
                    case 4:
                        saveClientsToFile(branch);
                        break;
                    case 5:
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
                        manageClientAccounts(branch, stdin, client);
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

        private static void manageClientAccounts(Branch branch, Scanner stdin, Client client) {
            loop:
            while(true) {
                displayMenu(client.getName() + " " + client.getSurname() + " - Manage accounts",
                        "Add account", "Edit account", "Remove account", "List all operations", "Go back");
                int choice = getInt(stdin);
                switch(choice) {
                    case 1:
                        addAccount(client, stdin);
                        break;
                    case 2:
                        editAccount(branch, client, stdin);
                    case 3:
                        AccountHandler.removeAccountHandler(client, stdin);
                        break;
                    case 4:
                        client.getAccounts().forEach(Account::printAllOperations);
                        break;
                    case 5:
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
                displayMenu(client.getName() + " " + client.getSurname() + " - Manage operations",
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

        public static void editAccount(Branch branch, Client client, Scanner stdin) {
            Account account = AccountHandler.chooseFromAccounts(client, stdin);
            if(account == null) {
                System.out.println("No accounts found.");
                return;
            } else if (account instanceof SavingsAccount) {
                loop:
                while(true) {
                    displayMenu(client.getName() + " " + client.getSurname() + " - Edit savings account",
                            "Change interest rate", "Change transfer fee", "Change withdraw fee", "Change account owner", "Go back");
                    int choice = getInt(stdin);
                    switch(choice) {
                        case 1:
                            AccountHandler.changeInterest((SavingsAccount) account, stdin);
                            break;
                        case 2:
                            AccountHandler.changeTransferFee((SavingsAccount) account, stdin);
                            break;
                        case 3:
                            AccountHandler.changeWithdrawFee((SavingsAccount) account, stdin);
                            break;
                        case 4:
                            AccountHandler.changeOwner(account, branch, stdin);
                            break;
                        case 5:
                            break loop;
                    }
                }
            } else {
                loop:
                while(true) {
                    displayMenu(client.getName() + " " + client.getSurname() + " - Edit regular account",
                            "Change account owner", "Go back");
                    int choice = getInt(stdin);
                    switch(choice) {
                        case 1:
                            AccountHandler.changeOwner(account, branch, stdin);
                            break;
                        case 2:
                            break loop;
                    }
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

    private static void saveClientsToFile(Branch branch) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("clients.dat"));
        for (Client client : branch.getClients()) {
            output.writeObject(client);
            System.out.println("Client " + client.getName() + " " + client.getSurname() + " was saved to file.");
        }
        output.close();
    }

    private static void readClientsFromFile(Branch branch) throws IOException, FileNotFoundException {

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("clients.dat"));
            try {
                while (true) {
                    Client client = (Client) input.readObject();
                    branch.addNewClient(client);
                    System.out.println("Client " + client.getName() + " " + client.getSurname() + " was read from file.");
                }
            } catch (EOFException exception) {}
            catch (IOException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File not found.");
        }
    }
}
