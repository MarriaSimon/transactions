package org.facttrackit.transactions.service.transaction;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.facttrackit.transactions.model.transaction.Transaction;
import org.facttrackit.transactions.model.transaction.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Getter
public class TransactionsService {
    private final TransactionsRepository transactionRepository;
    private final List<Transaction> transactionList = new ArrayList<>();


    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setProduct(updatedTransaction.getProduct());
            transaction.setType(updatedTransaction.getType());
            transaction.setAmount(updatedTransaction.getAmount());
            return transactionRepository.save(transaction);
        }
        return null;
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getAllTransactionsFilterable(String product, String type, double minAmount, double maxAmount) {
    return null;
    }
}
