package com.titancore.Service;

import com.titancore.database.BankAccountDao;
import com.titancore.domain.BankAccount;
import java.math.BigDecimal;
public class FundsTransferService {

    private final BankAccountDao accountDao ;

    public FundsTransferService(BankAccountDao accountDao){
        if (accountDao == null ){
            throw new IllegalArgumentException("Dependency 'accountDao' cannot be null");

        }
        this.accountDao = accountDao;
        System.out.println("[DI CONTAINER] FundsTransferService assembled successfully with its dependencies.");
    }
    public void transfer(BankAccount sender, BankAccount receiver, BigDecimal amount ){
        try {
            sender.withdraw(amount);
            this.accountDao.updateAccountBalance(sender.getAccountId(), sender.getBalance());

            if(receiver.getAccountId().equals("ACC-CRASH-001")){
                throw new RuntimeException("CRITICAL: Server crashed mid-transfer! Power outage!");
            }
            receiver.deposit(amount);
            this.accountDao.updateAccountBalance(receiver.getAccountId(), receiver.getBalance());
            System.out.println("[SERVICE LAYER] Transfer completed safely.");
        } catch ( Exception e){
            System.err.println("\n[TRANSACTION MANAGER] Error detected! Initiating ROLLBACK protocol...");

            sender.deposit(amount);
            this.accountDao.updateAccountBalance(sender.getAccountId(), sender.getBalance());
            System.out.println("[TRANSACTION MANAGER Rollback successful. Funds returned to sender.");

            throw new RuntimeException("Transfer aborted. State restored to original . ", e);
        }
    }
}
