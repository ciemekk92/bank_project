package com.company.banking.handlers;

import com.company.banking.models.Client;
import com.company.banking.models.accounts.Account;
import com.company.banking.util.UserInput;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;

import static com.company.banking.util.UserInput.getDouble;

public class OperationHandler {
    public static void makeTransfer(Account account, Scanner stdin) {
        System.out.println("Search for account to transfer to...");
        List<Client> clients = ClientHandler.searchClientsHandler(account.getOwner().getBranch(), stdin);
        if(clients == null) return;
        System.out.println("Choose a client by their index:");
        int index = UserInput.getInt(stdin, 0, clients.size() - 1);
        Client client = clients.get(index);
        Account payeeAccount = AccountHandler.chooseFromAccounts(client, stdin);
        if(payeeAccount == null) {
            System.out.println("Receiving client has no accounts.");
            return;
        }
        System.out.println(payeeAccount);
        System.out.println("Type in the amount to transfer:");
        double amount = getDouble(stdin, 0);
        if(amount == 0) {
            System.out.println("Operation cancelled.");
            return;
        }
        if(amount > account.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }
        account.sendTransfer(amount, payeeAccount);
        System.out.println("Transfer successful.");
    }

    public static void makeDeposit(Account account, Scanner stdin) {
        System.out.println("Type in the amount to deposit:");
        double amount = getDouble(stdin);
        account.newDeposit(amount);
        System.out.println("Deposited " + amount + ", current balance: " + account.getBalance());
    }

    public static void makeWithdrawal(Account account, Scanner stdin) {
        System.out.println("Type in the amount to withdraw:");
        double amount = getDouble(stdin);
        if(amount > account.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }
        account.newWithdrawal(amount);
        System.out.println("Withdrawed " + amount + ", current balance: " + account.getBalance());
    }
}
