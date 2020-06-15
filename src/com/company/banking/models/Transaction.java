package com.company.banking.models;

import com.company.banking.models.Account.Account;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private final double amount;
    private final Account payerAccount, payeeAccount;

    public Transaction(double amount, Account payerAccount, Account payeeAccount) {
        this.amount = amount;
        this.payerAccount = payerAccount;
        this.payeeAccount = payeeAccount;
    }

    public UUID getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Account getPayerAccount() {
        return payerAccount;
    }

    public Account getPayeeAccount() {
        return payeeAccount;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + id + "\nAmount: " + amount;
    }
}
