package com.titancore.titanbankapi.service;

import com.titancore.titanbankapi.domain.BankAccount;
import com.titancore.titanbankapi.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class FundsTransferService {
    private final BankAccountRepository accountRepository;

    public FundsTransferService(BankAccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Transactional
    public void transfer(String senderId, String receiverId, BigDecimal amount){
        System.out.println("\n[SPRING SERVICE] Initiating automated ACID transfer.. .");

        BankAccount sender = accountRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender account not found in database:" + senderId));
        BankAccount receiver = accountRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver account not found in database:" + receiverId));
        sender.withdraw(amount);
        receiver.deposit(amount);
        accountRepository.save(sender);
        accountRepository.save(receiver);

        System.out.println("[SPRING SERVICE ] Transfer completed safely. database synced. ");

    }
}
