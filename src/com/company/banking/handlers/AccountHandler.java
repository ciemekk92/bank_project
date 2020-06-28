package com.company.banking.handlers;

import com.company.banking.models.Client;
import com.company.banking.models.accounts.*;
import com.company.banking.util.UserInput;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Scanner;

import static com.company.banking.util.UserInput.*;

public class AccountHandler implements Serializable {
    public static Account chooseFromAccounts(Client client, Scanner stdin) throws EmptyStackException {
        if (client.getAccounts().size() == 0) {
            return null;
        }
        for (Account account : client.getAccounts()) {
            System.out.println("[" + client.getAccounts().indexOf(account) + "]\n" + account.toString());
        }
        System.out.println("Choose an account by its index:");
        int index = UserInput.getInt(stdin, 0, client.getAccounts().size() - 1);
        System.out.println(client.getAccounts().get(index));
        return client.getAccounts().get(index);
    }
    public static void listAccountsHandler(Client client, Scanner stdin) {
        System.out.println("Selected client's accounts:");
        if(client.getAccounts().size() != 0) client.printAllAccounts();
        else System.out.println("Client has no accounts.");
    }

    public static void addRegularAccount(Client client) {
        try {
            client.addNewAccount(new RegularAccount(client));
            System.out.println("Account added successfully to the current client.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    public static void addSavingsAccount(Client client, Scanner stdin) {
        System.out.println("Type in interest rate:");
        double inputInterest = getDouble(stdin);
        System.out.println("Type in transfer fee:");
        double inputTransfer = getDouble(stdin);
        System.out.println("Type in withdraw fee:");
        double inputWithdraw = getDouble(stdin);

        try {
            client.addNewAccount(new SavingsAccount(inputInterest, inputTransfer, inputWithdraw, client));
            System.out.println("Account added successfully to the current client.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }

    }

    public static void removeAccountHandler(Client client, Scanner stdin) {
        Account accountToRemove = chooseFromAccounts(client, stdin);
        if(accountToRemove == null) {
            System.out.println("No accounts found.");
            return;
        }
        client.removeAccount(accountToRemove);
    }
}
