package com.titancore.titanbankapi.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class BankAccount {
    @Id
    private String accountId;
    private BigDecimal balance;

    public BankAccount(){

    }
    public BankAccount(String accountId, BigDecimal balance){
        this.accountId = accountId;
        this.balance = balance;
    }
    public String getAccountId(){
        return accountId;
    }
    public BigDecimal getBalance(){
        return balance;
    }
    public void withdraw(BigDecimal amount){
        if (amount.compareTo(this.balance) > 0){
            throw new RuntimeException("Insufficient funds!");
        }
        this.balance = this.balance.subtract(amount);
    }
    public void deposit(BigDecimal amount){
        this.balance = this.balance.add(amount);
    }
}
