package com.titancore.titanbankapi.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
public class TransferRequest {

    @NotBlank(message = "Sender ID cannot be empty!")
    private String senderId;
    @NotBlank(message = "Sender ID cannot be empty!")
    private String receiverId;
    @Positive(message = "Transfer amount must be strictly greater than zero!")
    private BigDecimal amount;



    public TransferRequest() {}
    public TransferRequest(String senderId, String receiverId, BigDecimal amount ){
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public String getSenderId(){ return senderId;}
    public void setSenderId(String senderId){ this.senderId = senderId;}

    public String getReceiverId(){ return receiverId;}
    public void setReceiverId(String receiverId){this.receiverId = receiverId;}

    public BigDecimal getAmount(){ return amount;}
    public void setAmount(BigDecimal amount){ this.amount = amount;}
}

