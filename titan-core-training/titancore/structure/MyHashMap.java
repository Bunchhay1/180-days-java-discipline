package com.titancore.structure;

public class MyHashMap {

    private static final int INITIAL_CAPACITY = 16;

    private Entry[] buckets;

    public MyHashMap(){
        this.buckets = new Entry[INITIAL_CAPACITY];
    }

    private static class Entry {
        final String key ;
        String value;
        Entry next;

        public Entry(String key, String value, Entry next){
            this.key = key ;
            this.value = value;
            this.next = next;
        }
    }

    public void put(String key , String value){
        if (key == null){
            throw new IllegalArgumentException("Null keys not supported in this architecture ");
        }

        int hash = Math.abs(key.hashCode());

        int bucketIndex = hash % INITIAL_CAPACITY;

        Entry existing = buckets[bucketIndex];

        while (existing != null){
            if (existing.key.equals(key)){
                existing.value = value ;
                return ;
            }
            existing = existing.next;
        }
        buckets[bucketIndex] = new Entry(key, value, buckets[bucketIndex]);
        System.out.println("Inserted[" + key + " ] at Bucket Index:"+ bucketIndex);

    }
    public String get(String key){
        int hash = Math.abs(key.hashCode());
        int bucketIndex = hash % INITIAL_CAPACITY;
        Entry existing = buckets[bucketIndex];
        while (existing != null){
            if(existing.key.equals(key)){
                return existing.value;
            }
            existing = existing.next;
        }
        return null;
    }
}
