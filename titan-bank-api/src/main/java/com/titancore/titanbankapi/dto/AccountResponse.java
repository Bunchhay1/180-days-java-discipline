package com.titancore.titanbankapi.dto;

import java.math.BigDecimal;
public class AccountResponse {
    private String accountId;
    private BigDecimal balance;
    public AccountResponse(String accountId, BigDecimal balance){
        this.accountId = accountId;
        this.balance =balance;
    }
    //Getters
    public String getAccountId(){ return accountId;}
    public BigDecimal getBalance(){return balance;}
}
