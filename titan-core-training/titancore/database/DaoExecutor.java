package com.titancore.database;


import com.titancore.domain.BankAccount;
import java.math.BigDecimal;


public class DaoExecutor {
   public static void main(String[] args){
       System.out.println("--- Titan Core: Initializing DAO Integration Test ----");

       BankAccount account = new BankAccount("ACC-777", new BigDecimal("5000.00"));
       System.out.println("Initial Balance for " + account.getAccountId() + ": $" + account.getBalance());

       System.out.println("\n[BUSINESS LAYER] Processing withdraw of $1200.00...");
       account.withdraw(new BigDecimal("1200.00"));
       System.out.println("New in-Memory balance: $" + account.getBalance());

       System.out.println("\n[PERSISTENCE LAYER ] Saving new state to database ...");
       BankAccountDao dao = new BankAccountDao();
       dao.updateAccountBalance(account.getAccountId(), account.getBalance());
       System.out.println("\n---- DAO Integration Complete ---");
   }
}
