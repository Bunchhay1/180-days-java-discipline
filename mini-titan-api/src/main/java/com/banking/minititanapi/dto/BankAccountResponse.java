package com.banking.minititanapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record BankAccountResponse(
        String accountNumber,
        BigDecimal balance,
        LocalDateTime createdAt
){}

