package com.titancore.domain;

import java.math.BigDecimal;

public class ConcurrencyExecutor {
    public static void main(String[] args) {
        System.out.println("--- Titan Core: Initializing Concurrency Stress Test --- ");

        BankAccount jointAccount = new BankAccount("ACC-999", new BigDecimal("1000.00"));

        Runnable withdrawTask = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is trying to withdraw $800.00...");

            try {
                jointAccount.withdraw(new BigDecimal("800.00"));
                System.out.println(threadName + " SUCCESS! Remaining balance: $ " + jointAccount.getBalance());
            } catch(InsufficientFundsException e ){
                System.err.println(threadName + "FAILED: " + e.getMessage());
            }
        };

        Thread atmThread = new Thread(withdrawTask, "Thread-ATM");
        Thread mobileThread = new Thread(withdrawTask, "Thread-MobileApp");

        atmThread.start();
        mobileThread.start();
    }
}
