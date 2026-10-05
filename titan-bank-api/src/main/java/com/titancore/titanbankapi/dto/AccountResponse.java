package com.titancore.titanbankapi.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;


public class AccountResponse {
    private LocalDateTime updatedAt;
    private String accountId;
    private BigDecimal balance;

    public AccountResponse(String accountId, BigDecimal balance, LocalDateTime updatedAt){
        this.accountId = accountId;
        this.balance = balance;
        this.updatedAt = updatedAt;
    }
    //Getters
    public String getAccountId(){ return accountId;}
    public BigDecimal getBalance(){return balance;}
    public LocalDateTime getUpdatedAt() {return updatedAt;}
}
