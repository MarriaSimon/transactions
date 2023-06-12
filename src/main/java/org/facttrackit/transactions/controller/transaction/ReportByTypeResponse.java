package org.facttrackit.transactions.controller.transaction;

import org.facttrackit.transactions.model.transaction.Transaction;

import java.util.List;

public final class ReportByTypeResponse {
    public ReportByTypeResponse(List<Transaction> transactionList) {
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj != null && obj.getClass() == this.getClass();
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "ReportByTypeResponse[]";
    }

}
