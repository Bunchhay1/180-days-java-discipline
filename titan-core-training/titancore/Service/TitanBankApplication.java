package com.titancore.Service;


import com.titancore.database.BankAccountDao;
import com.titancore.domain.BankAccount;
import java.math.BigDecimal;

public class TitanBankApplication {
    public static void main(String[] args){
        System.out.println("----- Titan Core: Bootstrapping DI Container ---");
        BankAccountDao dao = new BankAccountDao();
        BankAccount sender = new BankAccount("ACC-999", new BigDecimal("5000.00"));
        BankAccount receiver = new BankAccount("ACC-CRASH-001", new BigDecimal("0.00"));
        FundsTransferService transferService = new FundsTransferService(dao);
        try {
            // ហៅមុខងារផ្ទេរប្រាក់ $1500 (មានទាំង sender ទាំង receiver)
            transferService.transfer(sender, receiver, new BigDecimal("1500.00"));
        } catch (Exception e) {
            System.err.println("Transaction Failed: " + e.getMessage());
        }


        System.out.println("--- DI Container Bootstrapped Successfully --- \n");
        BankAccount senderAccount = new BankAccount("ACC-999", new BigDecimal("5000.00"));

        //transferService.transfer(senderAccount, "ACC-TARGET-001", new BigDecimal("1500.00"));
        System.out.println("\n[AUDIT] sender Balance: $" + sender.getBalance());
        System.out.println("\n[AUDUT] Receiver Balance: $ " + receiver.getBalance());
    }

}
