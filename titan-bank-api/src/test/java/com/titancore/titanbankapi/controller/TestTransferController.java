package com.titancore.titanbankapi.controller;


import com.titancore.titanbankapi.service.TransferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/bank")
public class TestTransferController {

    private final TransferService transferService;

    public TestTransferController(TransferService transferService) {
        this.transferService = transferService;
    }
    @GetMapping("/transfer-test")
    public String doTransferTest(){
        return transferService.executeTransfer("ACC-1001", 500);
    }
}
