package org.facttrackit.transactions.model.transaction;

import jdk.jshell.Snippet;
import lombok.*;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    //    private TransactionType type;
    private String type;
    private double amount;

    public static Snippet builder() {
        return null;
    }


    public void setId(Object o) {
    }
}
