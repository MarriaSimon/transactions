package org.facttrackit.transactions.controller.transaction;

import org.facttrackit.transactions.model.transaction.TransactionType;


public record CreateTransactionRequest(String product, TransactionType type, double amount) {
}
