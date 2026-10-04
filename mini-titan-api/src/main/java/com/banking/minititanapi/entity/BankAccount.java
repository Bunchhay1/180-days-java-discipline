package com.banking.minititanapi.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false, unique = true, length = 20)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String userId;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Constructors
   public BankAccount() {
       this.createdAt = LocalDateTime.now();
   }

   public BankAccount(String accountNumber, String userId){
       this.accountNumber = accountNumber;
       this.userId = userId;
       this.balance = BigDecimal.ZERO;
       this.createdAt = LocalDateTime.now();
   }

    public Long getId(){ return id;}
    public String getAccountNumber(){ return accountNumber;}
    public void setAccountNumber(String accountNumber){ this.accountNumber = accountNumber;}
    public BigDecimal getBalance(){ return balance;}
    public void setBalance(BigDecimal balance){ this.balance = balance;}
    public String getUserId() { return userId;}
    public void setUserId(String userId) { this.userId = userId;}
}
