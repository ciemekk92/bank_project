package com.company.banking.models.accounts;

import java.io.Serializable;

public class SavingsAccount extends Account implements Serializable {
    private double interest, transferFee, withdrawFee;

    public SavingsAccount() {}

    public SavingsAccount(double interest, double transferFee, double withdrawFee) {
        this.interest = interest;
        this.transferFee = transferFee;
        this.withdrawFee = withdrawFee;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(double transferFee) {
        this.transferFee = transferFee;
    }

    public double getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(double withdrawFee) {
        this.withdrawFee = withdrawFee;
    }
}
