package org.facttrackit.transactions.budget;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("transactions") //http://post:port/transactions
public class TransactionsController {
    private final TransactionsService transactionsService;

    @GetMapping("/transactions") //http://localhost:8080/transactions/transactions
    public List<Transaction> getAll() {
        return transactionsService.getAllTransactions();
    }

    @GetMapping // http://localhost:8080/transactions?
    public List<Transaction> getAllFilterable(
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) double minAmount,
            @RequestParam(required = false) double maxAmount) {

        return transactionsService.getAllTransactionsFilterable(product, type, minAmount, maxAmount);
    }

    //GET /http://localhost:8080/transactions/5 - get transaction with id
    @GetMapping("/{id}")  //http://localhost:8080/transactions/5
    public Transaction getById(@PathVariable long id) {
        return transactionsService.getTransactionById(id);
    }

    //POST /transactions - adds a new transaction
    @PostMapping
    public Transaction addNewTransaction(@RequestBody Transaction transaction) {
        return transactionsService.addTransactions(transaction);
    }


    //PUT  /transactions/{id} - replaces the transaction with id
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable long id, @RequestBody Transaction transaction) {
        return transactionsService.updateTransaction(transaction, id);
    }

    //DELETE /http://localhost:8080/transactions/2 - deletes the transaction with id
    @DeleteMapping("/{id}")
    public Transaction deleteById(@PathVariable long id) {
        return transactionsService.deleteId(id);
    }

    //GET http://localhost:8080/transactions/reports/type -> returns a map from type to list of transactions of that type
    @GetMapping("reports/type")
    public Map<String, List<Transaction>> getTransactionByType() {
        return transactionsService.getTransactionsByType();
    }

    //GET /http://localhost:8080/transactions/reports/product -> returns a map from product to list of transactions for that product
    @GetMapping("reports/product")
    public Map<String, List<Transaction>> getTransactionByProduct() {
        return transactionsService.getTransactionByProduct();
    }
}
