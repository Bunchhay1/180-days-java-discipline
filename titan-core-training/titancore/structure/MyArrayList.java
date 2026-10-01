package com.titancore.structure;

import java.util.Arrays;


public class MyArrayList {

    private Object[] elements;

    private int size = 0;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(){
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public void add(Object e) {
        // If the array is full, we must expand it before adding a new element
        if (size == elements.length) {
            ensureCapacity();
        }
        // Assign the element and then increment the size (size++)
        elements[size++] = e;
    }
    private void ensureCapacity(){
        int newSize = elements.length * 2;

        elements = Arrays.copyOf(elements, newSize);

        System.out.println("System Log (Memory): Array Dynamically resized. New capacity = " + newSize);

    }
    public Object get(int i){

        if (i >= size || i < 0 ){
            throw new IndexOutOfBoundsException("Index:" + i + " Current Size: " + size);
        }
        return elements[i];
    }
}
