package com.company.banking.handlers;

import com.company.banking.models.Client;
import com.company.banking.models.accounts.*;

import java.io.Serializable;
import java.util.Scanner;

import static com.company.banking.Main.Menu.displayMenu;
import static com.company.banking.util.UserInput.*;

public class AccountHandler implements Serializable {
    public static void listAccountsHandler(Client client, Scanner stdin) {
        System.out.println("Selected clients accounts:");
        client.printAllAccounts();
    }

    public static void addAccountHandler(Client client, Scanner stdin) {
        displayMenu("Select account type", "Regular", "Savings", "Back to menu");


        loop:
        while(true) {
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

    public static void addRegularAccount(Client client) {
        try {
            client.addNewAccount(new RegularAccount(client));
            System.out.println("Account added successfully to the current client.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addSavingsAccount(Client client, Scanner stdin) {
        System.out.println("Type in interest rate");
        double inputInterest = getDouble(stdin);
        System.out.println("Type in transfer fee");
        double inputTransfer = getDouble(stdin);
        System.out.println("Type in withdraw fee");
        double inputWithdraw = getDouble(stdin);

        try {
            client.addNewAccount(new SavingsAccount(inputInterest, inputTransfer, inputWithdraw, client));
            System.out.println("Account added successfully to the current client.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void editAccountHandler(Client client, Scanner stdin) {
        // TODO
    }

    public static void removeAccountHandler(Client client, Scanner stdin) {
        // TODO
    }
}
