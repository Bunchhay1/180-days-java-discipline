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
    public void transfer(BankAccount sender, String receiverId, BigDecimal amount) {
        System.out.println("\n[SERVICE LAYER] Initiating transfer of $" + amount + " to account:" + receiverId);
        sender.withdraw(amount);
        this.accountDao.updateAccountBalance(sender.getAccountId(), sender.getBalance());
        System.out.println("[SERVICE LAYER Transfer completed safely.");
    }
}
