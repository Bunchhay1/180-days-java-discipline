package com.titancore.springbootcodesenior.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts") // បង្កើត Table ឈ្មោះ accounts
public class Account {

    @Id // ប្រាប់ថាវាជា Primary Key
    private String accountId;
    private double balance;

    // Constructor ទទេសម្រាប់ Spring JPA
    public Account() {}

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}