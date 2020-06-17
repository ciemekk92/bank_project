package com.company.banking.models.operations;

import java.util.UUID;

public abstract class Operation {
    protected UUID id;
    protected final double amount;

    public Operation(double amount) {
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
