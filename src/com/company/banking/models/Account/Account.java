package com.company.banking.models.Account;

import com.company.banking.models.Client;
import com.company.banking.models.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account { // TODO: Subclasses
    public String accountNumber;
    public double balance;
    private final List<Transaction> transactions = new ArrayList<>();
    private Client owner;

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public Client getOwner() {
        return owner;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public void printAllTransactions () {
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
        }
    }
}
