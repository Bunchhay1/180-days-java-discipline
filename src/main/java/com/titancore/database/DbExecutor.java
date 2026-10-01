package com.titancore.database;

public class DbExecutor {
    public static void main(String[] args){
        System.out.println("---Titan Core: Initializing Database Connection Manager ---");


        try(PostgresConnection conn = new PostgresConnection("CONM-5432")){
            conn.executeQuery("SELECT * FROM bank_account WHERE id = 'ACC-101'");
            conn.executeQuery("UPDATE bank_account SET balance = 1500 WHERE id = 'ACC-101'");
            conn.executeQuery("DROP TABLE bank_account;");

        } catch (Exception e) {
            System.err.println("Transaction failed:" + e.getMessage());
        }
        System.out.println("---- Execution Complete ----");
    }
}
