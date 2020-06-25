package com.company.banking.models.operations;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public abstract class Operation implements Serializable {
    protected UUID id;
    protected final double amount;
    private Instant date;

    public Operation(double amount) {
        this.amount = amount;
        this.date = Instant.now();
        this.id = UUID.randomUUID();
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

    public Instant getDate() { return date; }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Operation ID: " + id + "\nAmount: " + amount;
    }
}
