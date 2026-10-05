package com.titancore.titanbankapi.service;

import com.titancore.titanbankapi.domain.BankAccount;
import com.titancore.titanbankapi.repository.BankAccountRepository;
import org.slf4j.ILoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class FundsTransferService {

    private static final Logger log = LoggerFactory.getLogger(FundsTransferService.class);

    private final BankAccountRepository accountRepository;

    public FundsTransferService(BankAccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Transactional
    public void transfer(String senderId, String receiverId, BigDecimal amount){
        log.info("Initiating transfer of ${} to {} ", amount, senderId, receiverId);

        BankAccount sender = accountRepository.findById(senderId)
                .orElseThrow(() -> {
                    log.error("Transfer failed! Sender account {} not found.", senderId);
                    return new RuntimeException("Sender account not found in database.");
                        });
        BankAccount receiver = accountRepository.findById(receiverId)
                .orElseThrow(() -> {
                    log.error("Transfer failed! Receiver account {} not found.", receiverId);
                     return new RuntimeException("Receiver account not found in database.");
                });

        sender.withdraw(amount);
        receiver.deposit(amount);
        accountRepository.save(sender);
        accountRepository.save(receiver);


       log.info("[SPRING SERVICE ] Transfer completed safely. database synced. ");
    }
    public BankAccount getAcountDetial(String accountId){
        log.info("[SPRING SERVICE] Fetching account details for:" + accountId);
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found:" + accountId));
    }
    public Page<BankAccount> getHighValueAccounts(BigDecimal minimumBalance, int pageNumber, int pageSize){
        log.info("Investigating account > ${} | Page: {} | Size:  ${}", minimumBalance, pageNumber);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("balance").descending());
        return accountRepository.findByBalanceGreaterThan(minimumBalance, pageable);
    }
}
