package com.titancore.titanbankapi.service;


import com.titancore.titanbankapi.domain.BankAccount;
import com.titancore.titanbankapi.repository.BankAccountRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FundsTransferServiceTest {

    @Mock
    private BankAccountRepository accountRepository;
    @InjectMocks
    private FundsTransferService transferService;
    @Test
    public void shouldTransferSuccessfully(){
        BankAccount sender = new BankAccount("ACC-001", new BigDecimal("1000.00"));
        BankAccount receiver = new BankAccount("ACC-002", new BigDecimal("500.00"));
        when(accountRepository.findByIdForUpdate("ACC-001")).thenReturn(Optional.of(sender));
        when(accountRepository.findByIdForUpdate("ACC-002")).thenReturn(Optional.of(receiver));
        transferService.transfer("ACC-001", "ACC-002", new BigDecimal("200.00"));
        assertEquals(new BigDecimal("800.00"), sender.getBalance());
        assertEquals(new BigDecimal("700.00"), receiver.getBalance());
        verify(accountRepository, times(2)).save(any(BankAccount.class));
    }
    @Test
    public void shouldThrowExceptionInsuffientFunds(){
        BankAccount sender = new BankAccount("ACC-001", new BigDecimal("100.00"));
        BankAccount receiver = new BankAccount("ACC-002", new BigDecimal("500.00"));

        when(accountRepository.findByIdForUpdate("ACC-001")).thenReturn(Optional.of(sender));
        when(accountRepository.findByIdForUpdate("ACC-002")).thenReturn(Optional.of(receiver));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transferService.transfer("ACC-001", "ACC-002", new BigDecimal("500.00"));
        });
        assertEquals("Insufficient funds!", exception.getMessage());
    }
}















