package com.titancore.springbootcodesenior.exception;


import com.titancore.springbootcodesenior.dto.Response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHander {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHander.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidInput(IllegalArgumentException ex) {
        log.error("Client Error: {}" , ex.getMessage());

        ApiResponse<Void> response = new ApiResponse<>(false, ex.getMessage(), null);

        // bring HTTP 400 Bad request to mobile app
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
        // select error ex nullpointerException
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleSystemCrash(Exception ex){
        log.error("CRITICAL SYSTEM FAILURE: {}" , ex.getMessage());

        ApiResponse<Void> respnose = new ApiResponse<>(false, "Internal Core Banking System Error", null);

        return new ResponseEntity<>(respnose, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
