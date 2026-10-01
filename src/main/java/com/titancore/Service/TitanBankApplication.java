package com.titancore.Service;


import com.titancore.database.BankAccountDao;
import com.titancore.domain.BankAccount;
import java.math.BigDecimal;

public class TitanBankApplication {
    public static void main(String[] args){
        System.out.println("----- Titan Core: Bootstrapping DI Container ---");
        BankAccountDao dao = new BankAccountDao();

        FundsTransferService transferService = new FundsTransferService(dao);
        System.out.println("--- DI Container Bootstrapped Successfully --- \n");
        BankAccount senderAccount = new BankAccount("ACC-999", new BigDecimal("5000.00"));
        transferService.transfer(senderAccount, "ACC-TARGET-001", new BigDecimal("1500.00"));
    }

}
