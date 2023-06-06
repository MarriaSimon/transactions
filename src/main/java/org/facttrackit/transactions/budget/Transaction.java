package org.facttrackit.transactions.budget;

import lombok.*;


@Builder


public record Transaction(long id, String product, String type, double amount) {
    public long id() {
        return id;
    }

    public String product() {
        return product;
    }

    public String type() {
        return type;
    }

    public double amount() {
        return amount;
    }
}
