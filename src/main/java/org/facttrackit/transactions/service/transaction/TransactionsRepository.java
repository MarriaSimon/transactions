package org.facttrackit.transactions.service.transaction;

import org.facttrackit.transactions.model.transaction.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends CrudRepository<Transaction, Long> {
}
