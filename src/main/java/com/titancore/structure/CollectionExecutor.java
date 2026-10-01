package com.titancore.structure;


public class CollectionExecutor {

    public static void main(String[] args){
        System.out.println("---Titan Core: Initializing Custom ArrayList ---");
        MyArrayList list = new MyArrayList();

        for (int i = 1 ; i <=12; i++){
            list.add("Transaction-Log-" + i);
            System.out.println("Inserted: Transaction-Log-"+ i);
        }
        System.out.println("\n--- Validating Data Integrity ---");
        System.out.println("Retrieving Index 5:" + list.get(5));
        System.out.println("Retrieving Index 11:" + list.get(11));

        System.out.println("---- Execution Complete ----");


        System.out.println(list.get(15));
    }
}
