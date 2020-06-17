package com.company.banking.models.operations;

import com.company.banking.models.accounts.Account;

public class Withdrawal extends Operation {
    private final Account account;

    public Withdrawal(double amount, Account account) {
        super(amount);
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
