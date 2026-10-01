package com.titancore.structure;

public class HashMapExecutor {

    public static void main(String[] args){
        System.out.println("--- titan Core: Initializing Custom HashMap ---");

        MyHashMap map = new MyHashMap();
            System.out.println("\n--- Inserting Data ---");
            map.put("TXN-101", "Deposit $500");
            map.put("TXN-102", "Withdraw $200");
            map.put("TXN-103", "Deposit $1000");

        System.out.println("\n---- Updating Existing key ---");
        map.put("TXN-102", "Withdraw $250 (Updated by System");

        System.out.println("\n--- Validating Data Retrieval ----");
        System.out.println("Retrieve TXN-102:" + map.get("TXN-101"));
        System.out.println("Retrieve TXN-102:" + map.get("TXN-102"));
        System.out.println("Retrieve TXN-999:" + map.get("TXN-999"));

        System.out.println("\n---- Execution Complete ----");

    }
}
