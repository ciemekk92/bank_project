package com.company.banking.models.accounts;

import com.company.banking.models.Client;
import com.company.banking.models.operations.*;
import com.company.banking.util.AccountNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account implements Serializable {
    public String accountNumber = AccountNumber.generateAccountNumber();
    public double balance = 0;
    public final List<Operation> operations = new ArrayList<>();
    public Client owner;

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Operation> getOperations() {
        return Collections.unmodifiableList(operations);
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

    public void addOperation(Operation operation) {
        operations.add(operation);
    }

    public void removeOperation(Operation operation) {
        operations.remove(operation);
    }

    public void printAllOperations() {
        for (Operation operation : operations) {
            System.out.println(operation.toString());
        }
    }

    @Override
    public String toString() {
        return "Account: " + accountNumber + " Owner: " + owner.getName() + " " + owner.getSurname() +"." + " Balance: " + balance;
    }

    public void receiveTransfer(double amount, Account payerAccount) {
        Transaction operation = new Transaction(amount, payerAccount, this);
        balance += amount;
        this.addOperation(operation);
    }

    public void sendTransfer (double amount, Account payeeAccount) {
        Transaction operation = new Transaction(amount, this, payeeAccount);
        this.addOperation(operation);
        if (this instanceof SavingsAccount) {
            balance = balance - amount - ((SavingsAccount) this).getTransferFee();
        } else {
            balance -= amount;
        }
        payeeAccount.receiveTransfer(amount, this);
    }

    public void newWithdrawal (double amount) {
        Withdrawal withdrawal = new Withdrawal(amount, this);
        this.addOperation(withdrawal);
        if (this instanceof SavingsAccount){
            balance = balance - amount - ((SavingsAccount) this).getWithdrawFee();
        } else {
            balance -= amount;
        }
    }

    public void newDeposit (double amount) {
        Deposit deposit = new Deposit(amount, this);
        this.addOperation(deposit);
        balance += amount;
    }
}
