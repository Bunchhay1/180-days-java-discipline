package com.titancore.springbootcodesenior.service;

import com.titancore.springbootcodesenior.dto.Request.TransferRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    // Spring open database transaction ( rollback when error )
    @Transactional
    public String processEnterpriseTransfer(TransferRequest transferRequest){
        log.info("Initiating transfer of ${} from {} to {}",
                transferRequest.amount(), transferRequest.senderAccountId(), transferRequest.receiverAccountId());

        // block business rule : detect transfer - value
        if (transferRequest.amount() <= 0){
            log.warn("transfer failed: Invalid amount {}",transferRequest.amount());

            // take for message error to give exception @RestControllerAdvice
            throw new IllegalArgumentException("Transfer amount must be greater than zero");

        }
        log.info("connecting to database to check balance for account: {}", transferRequest.senderAccountId());

        double currentDatabaseBalance = 100.0;
        if (transferRequest.amount() < currentDatabaseBalance){
            log.error("transfer failed: Insufficient funds account {}", transferRequest.senderAccountId());
            throw new IllegalArgumentException("Insufficient funds account " + currentDatabaseBalance);
        }
        log.info("transfer succeeded for account: {}", transferRequest.amount());

        log.info("Transfer successful");
        return "Transfer TX-9994 successful";

        }
    }

