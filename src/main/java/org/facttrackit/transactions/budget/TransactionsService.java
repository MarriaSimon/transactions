package org.facttrackit.transactions.budget;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Getter
public class TransactionsService {
    private Transaction transaction;
    private final List<Transaction> transactionList = new ArrayList<>();


    @PostConstruct
    public void initializeTransactions() {
        Transaction transaction1 = Transaction.builder()
                .id(1)
                .product("Fridge")
                .type("SELL")
                .amount(138.99)
                .build();

        Transaction transaction2 = Transaction.builder()
                .id(2)
                .product("Shampoo")
                .type("BUY")
                .amount(13.10)
                .build();

        Transaction transaction3 = Transaction.builder()
                .id(3)
                .product("Laptop")
                .type("BUY")
                .amount(510.10)
                .build();

        Transaction transaction4 = Transaction.builder()
                .id(4)
                .product("Monitor")
                .type("SELL")
                .amount(99.9)
                .build();

        Transaction transaction5 = Transaction.builder()
                .id(5)
                .product("Phone")
                .type("SELL")
                .amount(230.5)
                .build();

        Transaction transaction6 = Transaction.builder()
                .id(6)
                .product("Headphone")
                .type("BUY")
                .amount(25)
                .build();

        Transaction transaction7 = Transaction.builder()
                .id(7)
                .product("Phone")
                .type("SELL")
                .amount(120.5)
                .build();
        Transaction transaction8 = Transaction.builder()
                .id(8)
                .product("Fridge")
                .type("BUY")
                .amount(235.4)
                .build();
        Transaction transaction9 = Transaction.builder()
                .id(9)
                .product("Monitor")
                .type("BUY")
                .amount(55.6)
                .build();
        Transaction transaction10 = Transaction.builder()
                .id(10)
                .product("Headphone")
                .type("BUY")
                .amount(299.99)
                .build();

        transactionList.add(transaction1);
        transactionList.add(transaction2);
        transactionList.add(transaction3);
        transactionList.add(transaction4);
        transactionList.add(transaction5);
        transactionList.add(transaction6);
        transactionList.add(transaction7);
        transactionList.add(transaction8);
        transactionList.add(transaction9);
        transactionList.add(transaction10);


    }

    public Transaction addTransactions(Transaction transaction) {
        transactionList.add(transaction);
        return transaction;
    }

    //GET /transactions - get all transactions.
    public List<Transaction> getAllTransactions() {

        return transactionList;
    }

    //GET /transactions - get all transactions filterable by product, type, minAmount, maxAmount.
    public List<Transaction> getAllTransactionsFilterable(String product, String type, double minAmount, double maxAmount) {
        return transactionList.stream()
                .filter(transaction -> product == null || transaction.product().equalsIgnoreCase(product))
                .filter(transaction -> type == null || transaction.type().equalsIgnoreCase(type))
                .filter(transaction -> minAmount == 0 || transaction.amount() >= minAmount)
                .filter(transaction -> maxAmount == 0 || transaction.amount() <= maxAmount)
                .toList();
    }


    //GET /transactions/{id} - get transaction with id
    public Transaction getTransactionById(long id) {
        return transactionList.stream()
                .filter(transaction -> transaction.id() == id)
                .findFirst()
                .orElse(null);
    }

    //POST//  transactions - adds a new transaction
    public Transaction addTransaction(Transaction transaction) {
        transactionList.add(transaction);
        return transaction;
    }

    //    PUT  /transactions/{id} - replaces the transaction with id
    public Transaction updateTransaction(Transaction transaction, long id) {
        Transaction existingTransaction = getTransactionById(id);
        if (existingTransaction == null) {
            return null;
        }

        Transaction updatedTransaction = Transaction.builder()
                .id(id)
                .product(transaction.product())
                .type(transaction.type())
                .amount(existingTransaction.amount())
                .build();
        transactionList.remove(existingTransaction);
        transactionList.add(updatedTransaction);
        return updatedTransaction;
    }


    //    DELETE /transactions/{id} - deletes the transaction with id
    public Transaction deleteId(long id) {
        Transaction transaction = getTransactionById(id);
        transactionList.remove(transaction);
        return transaction;
    }

    //GET /transactions/reports/type -> returns a map from type to list of transactions of that type
    public Map<String, List<Transaction>> getTransactionsByType() {
        return transactionList.stream()
                .collect(Collectors.groupingBy(Transaction::type));
    }

    // GET /transactions/reports/product -> returns a map from product to list of transactions for that product
    public Map<String, List<Transaction>> getTransactionByProduct() {
        return transactionList.stream()
                .collect(Collectors.groupingBy(Transaction::product));
    }

}
