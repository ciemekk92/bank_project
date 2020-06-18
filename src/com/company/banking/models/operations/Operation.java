package com.company.banking.models.operations;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Operation implements Serializable {
    protected UUID id;
    protected final double amount;
    private LocalDateTime date;

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

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Operation ID: " + id + "\nAmount: " + amount;
    }
}
