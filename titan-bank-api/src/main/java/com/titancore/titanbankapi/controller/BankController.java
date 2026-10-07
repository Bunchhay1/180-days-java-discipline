package com.titancore.titanbankapi.controller;

import com.titancore.titanbankapi.service.TransferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    private final TransferService transferService;

    public BankController(TransferService transferService) {
        this.transferService = transferService;
    }

    // ផ្លូវទី ១៖ សម្រាប់ឆែកមើលថា Controller នេះដើរឬអត់ (Health Check)
    @GetMapping("/ping")
    public String ping() {
        return "Bank API is LIVE and visible to Spring Boot!";
    }

    // ផ្លូវទី ២៖ កូដវេរលុយ និងសាកល្បង Circuit Breaker របស់អ្នក
    @GetMapping("/transfer-test")
    public String doTransferTest(){
        return transferService.executeTransfer("ACC-1001", 500);
    }
}