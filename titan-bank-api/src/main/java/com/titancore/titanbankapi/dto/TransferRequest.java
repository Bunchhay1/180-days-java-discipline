package com.titancore.titanbankapi.dto;

import java.math.BigDecimal;

// DTO (Data Transfer Object) - គ្រាន់តែជាស្រោមសំបុត្រសម្រាប់ផ្ទុកទិន្នន័យពី Mobile App
public class TransferRequest {

    private String senderId;
    private String receiverId;
    private BigDecimal amount;

    // Constructors
    public TransferRequest() {}

    public TransferRequest(String senderId, String receiverId, BigDecimal amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    // Getters and Setters (ចាំបាច់បំផុតដើម្បីឱ្យ Spring Boot អាចបញ្ចូលទិន្នន័យ JSON ចូលទីនេះបាន)
    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getReceiverId() { return receiverId; }
    public void setReceiverId(String receiverId) { this.receiverId = receiverId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}