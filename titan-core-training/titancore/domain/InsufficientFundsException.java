package com.titancore.domain;


public class InsufficientFundsException  extends RuntimeException {

    public InsufficientFundsException( String message ) {
        super (message);
    }
}
