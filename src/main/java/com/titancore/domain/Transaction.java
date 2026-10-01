package com.titancore.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Transaction {

    public enum Type{
        DEPOSITE, WITHDRAWAL, TRANSFER
    }
    public enum Status{
        SUCCESS, FAILED, PENDING
    }
    private final String transactionId;
    private final String accountId;
    private final BigDecimal amount;
    private final Type type;
    private final Status status;
    private final LocalDateTime timestamp;

    public Transaction(String transactionId, String accountId, BigDecimal amount, Type type , Status status ){
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
    public String getTransactionId(){ return transactionId;}
    public String getAccountId(){ return accountId;}
    public BigDecimal getAmount(){ return amount;}
    public Type getType(){ return type;}
    public Status getStatus(){ return status;}
    public LocalDateTime getTimestamp(){ return timestamp;}

    @Override
    public String toString(){
        return String.format("TXN[%s] | %s | %s | Amt: $%s | Status: %s", transactionId, timestamp,type, amount, status);
    }
}

