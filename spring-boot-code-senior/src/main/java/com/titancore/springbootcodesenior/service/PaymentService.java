package com.titancore.springbootcodesenior.service;


import com.titancore.springbootcodesenior.dto.Request.TransferRequest;
import com.titancore.springbootcodesenior.entity.Account;
import com.titancore.springbootcodesenior.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    // ប្រើប្រាស់ Repository ដែលទើបនឹងបង្កើត
    private final AccountRepository accountRepository;

    public PaymentService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public String processEnterpriseTransfer(TransferRequest request) {
        log.info("Initiating transfer for amount ${}", request.amount());

        if (request.amount() <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero");
        }

        // 1. ទាញយកគណនីអ្នកផ្ញើពី Database ពិតប្រាកដ
        Account sender = accountRepository.findById(request.senderAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Sender account not found!"));

        // 2. ទាញយកគណនីអ្នកទទួល
        Account receiver = accountRepository.findById(request.receiverAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Receiver account not found!"));

        // 3. ឆែកមើលលុយពិតប្រាកដ
        if (request.amount() > sender.getBalance()) {
            throw new IllegalStateException("Insufficient funds. Your balance is only $" + sender.getBalance());
        }

        // 4. ធ្វើការដកលុយ និងបញ្ចូលលុយ
        sender.setBalance(sender.getBalance() - request.amount());
        receiver.setBalance(receiver.getBalance() + request.amount());

        // 5. រក្សាទុកត្រលប់ទៅ Database វិញ (Update)
        accountRepository.save(sender);
        accountRepository.save(receiver);

        log.info("Transfer TX-9994 successful");
        return "Transfer TX-9994 successful. New Balance: $" + sender.getBalance();
    }
}