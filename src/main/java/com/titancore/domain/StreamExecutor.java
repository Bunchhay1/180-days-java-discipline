package com.titancore.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StreamExecutor {
    public static void main (String[] args){
        System.out.println("--- Titan Core: Initializing Stream Processing ---");

        List<Transaction> mockDatabase = Arrays.asList(
                new Transaction("TXN-001", "ACC-101", new BigDecimal("1500.00"),Transaction.Type.DEPOSITE, Transaction.Status.SUCCESS),
                new Transaction("TXN-002", "ACC-102", new BigDecimal("300.00"), Transaction.Type.WITHDRAWAL,Transaction.Status.SUCCESS),
                new Transaction("TXN-003", "ACC-101", new BigDecimal("5000.00"), Transaction.Type.DEPOSITE, Transaction.Status.FAILED),
                new Transaction("TXN-004", "ACC-103", new BigDecimal("250.00"), Transaction.Type.TRANSFER, Transaction.Status.PENDING),
                new Transaction("TXN-005", "ACC-102",  new BigDecimal("700.00"), Transaction.Type.DEPOSITE, Transaction.Status.SUCCESS)

        );
        TransactionStreamService service = new TransactionStreamService();
        System.out.println("\n[1] Successful Transaction IDs:");
        List<String> successIds = service.getSuccessfulTransactionIds(mockDatabase);
        successIds.forEach(id -> System.out.println(" ->" +id));

        System.out.println("\n[2] Total Successful Deposits:");
        BigDecimal totalDeposits = service.calculateTotalDeposite(mockDatabase);
        System.out.println(" -> $"+ totalDeposits);

        System.out.println("\n[3] Transactions Grouped By Status:");
        Map<Transaction.Status, List<Transaction>> grouped = service.groupTransactionsByStatus(mockDatabase);

        grouped.forEach((status, txns) -> {
            System.out.println("Status: " + status);
            txns.forEach(txn -> System.out.println("    " + txn));

        });

        System.out.println("\n[4] AML Audit - Highest Successful Deposit:");
        java.util.Optional<Transaction> highestDeposit = service.getHighestSuccessfulDeposit(mockDatabase);
        if (highestDeposit.isPresent()){
            System.out.println(" -> ALERT: Found massive deposit: " + highestDeposit.get());
        } else {
            System.out.println(" -> No successful deposits found today.");
        }


        System.out.println("\n--- Processing Complete ----");
    }
}
