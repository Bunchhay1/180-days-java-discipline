package com.titancore.domain;

import java.math.BigDecimal;
import java.util.Objects;


public class BankAccount implements AccountOperations {

    private final String accountId;
    private BigDecimal balance;

    public BankAccount(String accountId, BigDecimal initialBalance){
        if (initialBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Initial balance cannot be negative");

        }
        this.accountId = accountId;
        this.balance = initialBalance;
    }
    @Override
    public synchronized void deposit(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        this.balance = this.balance.add(amount);
    }
    @Override
    public synchronized void  withdraw(BigDecimal amount){
        if (amount.compareTo(this.balance) > 0) {
            throw new InsufficientFundsException(" Transaction failed: Insufficient funds in account " + this.accountId);
        }
        this.balance = this.balance.subtract(amount);
    }
    public String getAccountId(){
        return this.accountId;
    }
    @Override
    public BigDecimal getBalance(){
        return this.balance;
    }
    @Override
    public boolean equals(Object o){
        if ( this == o) return true;
        if (o == null ||getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o ;
        return Objects.equals(accountId, that.accountId);
    }
    @Override
    public int hashCode(){
        return Objects.hash(accountId);
    }
    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId'" + accountId + '\'' +
                ", balance=" + balance +
                "}";
    }
}
