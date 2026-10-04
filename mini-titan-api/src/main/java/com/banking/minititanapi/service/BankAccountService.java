package com.banking.minititanapi.service;

import com.banking.minititanapi.entity.BankAccount;
import com.banking.minititanapi.repository.BankAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankAccountService {
    private static final Logger log = LoggerFactory.getLogger(BankAccountService.class);
    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository){
        this.repository = repository;
    }
    @Transactional
    public BankAccount createAccount(String accountNumber, String tokenUserId){
        log.info("Creating new account {} for User ID {}", accountNumber, tokenUserId);

        if (repository.findByAccountNumber(accountNumber).isPresent()){
            throw new IllegalArgumentException("Account number already exists is the system!");
        }
            BankAccount newAccount = new BankAccount(accountNumber, tokenUserId);
            return repository.save(newAccount);


    }

    @Transactional(readOnly = true)
    public BankAccount getAccountBalanceSecured(String accountNumber, String tokenUserId){
        BankAccount account = repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found!"));

        if (!account.getUserId().equals(tokenUserId)){
            log.error("SECURITY ALERT: User {} attempted IDOR on account {}", tokenUserId, accountNumber);
            throw new AccessDeniedException("you are not authorized to access this account.");
        }

        return account;
    }

}