package com.titancore.domain;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TransactionStreamService {

    public List<String> getSuccessfulTransactionIds(List<Transaction> transactions){
        return transactions.stream()
                .filter(txn -> txn.getStatus() == Transaction.Status.SUCCESS)
                .map(Transaction::getTransactionId)
                .collect(Collectors.toList());
    }

    public BigDecimal calculateTotalDeposite(List<Transaction> transactions){
        return transactions.stream()
                .filter(txn -> txn.getType() == Transaction.Type.DEPOSITE)
                .filter(txn -> txn.getStatus() == Transaction.Status.SUCCESS)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        }

        public Map<Transaction.Status, List<Transaction>> groupTransactionsByStatus(List<Transaction> transactions){
            return transactions.stream()
                    .collect(Collectors.groupingBy(Transaction::getStatus));
    }
    public java.util.Optional<Transaction> getHighestSuccessfulDeposit(List<Transaction> transactions){
        return transactions.stream()
                .filter(txn -> txn.getType() == Transaction.Type.DEPOSITE)
                .filter(txn -> txn.getStatus() == Transaction.Status.SUCCESS)
                .max(java.util.Comparator.comparing(Transaction::getAmount));
    }
}
