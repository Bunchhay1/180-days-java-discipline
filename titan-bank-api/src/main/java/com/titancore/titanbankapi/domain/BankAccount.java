package com.titancore.titanbankapi.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jdk.jshell.execution.LoaderDelegate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class BankAccount {
    @Id
    private String accountId;
    private BigDecimal balance;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

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
    public LocalDateTime getCreatedAt(){ return createdAt;}
    public LocalDateTime getUpdatedAt(){ return updatedAt;}
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
