package com.banking.minititanapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAccountRequest (

    @NotBlank(message = "Account number is strictly required.")
    @Size(min = 9, max = 20, message = "Account number length must be strictly between 9 and 20 characters.")
    String accountNumber

) {}