package com.titancore.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TransactionProcessor {

    public static void main(String[] args){

        List<BankAccount > accounts = Arrays.asList(
                new BankAccount("ACC-001", new BigDecimal("1500.00")),
                new BankAccount("ACC-002", new BigDecimal("300.00")),
                new BankAccount("ACC-003", new BigDecimal("5000.00")),
                new BankAccount("ACC-004", new BigDecimal("50.00"))
        );
        System.out.println("----Titan Core: Initializing transaction Processor --");


        accounts.get(1).withdraw(new BigDecimal("1000.00"));
        List<String> highValueAccountIds = accounts.stream()

                .filter(acc -> acc.getBalance().compareTo(new BigDecimal("1000.00")) > 0)
                .map(BankAccount::toString)
                .collect(Collectors.toList());

        System.out.println("High Value Account Detected:");
        highValueAccountIds.forEach(System.out::println);

        System.out.println("----- Processing Complete ---");
    }
}
