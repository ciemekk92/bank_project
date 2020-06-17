package com.company.banking.models.operations;

import com.company.banking.models.accounts.Account;

public class Transaction extends Operation {
    private final Account payerAccount, payeeAccount;

    public Transaction(double amount, Account payerAccount, Account payeeAccount) {
        super(amount);
        this.payerAccount = payerAccount;
        this.payeeAccount = payeeAccount;
    }

    public Account getPayerAccount() {
        return payerAccount;
    }

    public Account getPayeeAccount() {
        return payeeAccount;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + id + "\nAmount: " + amount;
    }
}
