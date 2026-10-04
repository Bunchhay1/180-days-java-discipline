package com.banking.minititanapi.enums;

public enum ErrorCode {

    BUSINNESS_RULE_VIOLATION("4001"),
    VALIDATION_FAILED("4002"),
    UNAUTHORIZED_ACCESS("4003");

    private final String code;
    ErrorCode(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
}
