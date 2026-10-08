package com.titancore.springbootcodesenior.dto.Request;

public record TransferRequest(
        String senderAccountId,
        String receiverAccountId,
        double amount
) {}
