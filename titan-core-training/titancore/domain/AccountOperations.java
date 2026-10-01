package com.titancore.domain;

import java.math.BigDecimal;

public interface AccountOperations{
    void deposit(BigDecimal amount);

    void withdraw(BigDecimal amount);

    BigDecimal getBalance();
}