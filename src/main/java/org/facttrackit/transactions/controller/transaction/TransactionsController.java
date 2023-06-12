package org.facttrackit.transactions.controller.transaction;

import lombok.RequiredArgsConstructor;
import org.facttrackit.transactions.model.transaction.Transaction;
import org.facttrackit.transactions.service.transaction.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("transactions") //http://post:port/transactions
public class TransactionsController {
    private final TransactionsService transactionsService;


    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionsService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long id) {
        return transactionsService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionsService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(
            @PathVariable("id") Long id,
            @RequestBody Transaction updatedTransaction
    ) {
        Transaction transaction = transactionsService.updateTransaction(id, updatedTransaction);
        return transaction != null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") Long id) {
        transactionsService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reports/type")
    public ResponseEntity<Map<String, List<Transaction>>> getTypeReport() {
        List<Transaction> transactions = transactionsService.getAllTransactions();
        Map<String, List<Transaction>> typeReport = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getType));
        return ResponseEntity.ok(typeReport);
    }

    @GetMapping("/reports/product")
    public ResponseEntity<Map<String, List<Transaction>>> getProductReport() {
        List<Transaction> transactions = transactionsService.getAllTransactions();
        Map<String, List<Transaction>> productReport = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getProduct));
        return ResponseEntity.ok(productReport);
    }
}
