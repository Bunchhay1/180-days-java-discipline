package com.titancore.database;


import com.titancore.domain.BankAccount;
import java.math.BigDecimal;

public class BankAccountDao {
    public void updateAccountBalance(String accountId, BigDecimal newBalance) {
        String sql = String.format("UPDATE bank_account SET balance = %s WHERE id = '%s", newBalance,  accountId);
        System.out.println("[DAO LATER] Preparing to update account:  " + accountId);

        try (PostgresConnection conn = new PostgresConnection("CONN-DAO-888")){
            conn.executeQuery(sql);
            System.out.println("[DAO LAYER] Update successful for " + accountId);

        } catch ( Exception e ){
            System.err.println("DAO LAYER] Database failure " + e.getMessage());
            throw new RuntimeException("Failed to update database for account:" + accountId);
        }
    }
}
