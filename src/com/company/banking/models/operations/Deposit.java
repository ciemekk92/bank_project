package com.company.banking.models.operations;

import com.company.banking.models.accounts.Account;

public class Deposit extends Operation {
    private final Account account;

    public Deposit(double amount, Account account) {
        super(amount);
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "Deposit ID: " + id + "\nAmount: " + amount + "\nAccount: " + account;
    }
}
