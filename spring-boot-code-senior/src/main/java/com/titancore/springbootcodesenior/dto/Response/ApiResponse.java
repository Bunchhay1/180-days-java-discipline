package com.titancore.springbootcodesenior.dto.Response;

import java.time.LocalDateTime;
public class ApiResponse<T>{
    private final boolean success;
    private final String message;
    private final T data;
    private final LocalDateTime timestamp;

    // T for generic can be get data for ( string object list )
    public ApiResponse(boolean success, String message, T data){
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // getter for jackson ( Spring's JSON converter ) to read the data
    public boolean isSuccess() { return success; }
    public String getMessage(){return message;}
    public T getData(){return data;}
    public String getTimestamp(){return timestamp.toString();}
}
