package com.titancore.titanbankapi.controller;

import com.titancore.titanbankapi.domain.BankAccount;
import com.titancore.titanbankapi.dto.TransFerRequest;
import com.titancore.titanbankapi.service.FundsTransferService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/accounts")
public class BankAccountController {
    private final FundsTransferService transferService;
    public BankAccountController(FundsTransferService transferService){
        this.transferService = transferService;
    }
    @PostMapping("/transfer")
    public String performTransfer(@RequestBody TransFerRequest request){
        System.out.println("\n[API LAYER] Receiver transfer request from Mobile App....");

        transferService.transfer(
                request.getSenderId(),
                request.getReceiverId(),
                request.getAmount()
        );

        return "Transaction Successful: Transferred $" + request.getAmount() + " to " + request.getReceiverId();
    }
}
