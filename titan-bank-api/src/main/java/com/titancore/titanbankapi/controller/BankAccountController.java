package com.titancore.titanbankapi.controller;

import com.titancore.titanbankapi.domain.BankAccount;
import com.titancore.titanbankapi.dto.AccountResponse;
import com.titancore.titanbankapi.dto.TransferRequest;
import com.titancore.titanbankapi.service.FundsTransferService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Page;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/accounts")
public class BankAccountController {
    private final FundsTransferService transferService;
    public BankAccountController(FundsTransferService transferService){
        this.transferService = transferService;
    }
    @PostMapping("/transfer")
    public String performTransfer(@Valid  @RequestBody TransferRequest request){
        System.out.println("\n[API LAYER] Receiver transfer request from Mobile App....");

        transferService.transfer(
                request.getSenderId(),
                request.getReceiverId(),
                request.getAmount()
        );

        return "Transaction Successful: Transferred $" + request.getAmount() + " to " + request.getReceiverId();
    }
    @GetMapping("/{id}")
    public AccountResponse checkBalance(@PathVariable String id){
        System.out.println("[API LAYER] Receiver balance inquiry for accouint:" + id);
        BankAccount account = transferService.getAcountDetial(id);
        return new AccountResponse(account.getAccountId(), account.getBalance());
    }
    @GetMapping("/search")
    public Page<AccountResponse> searchAccounts(@RequestParam(name = "minBalance") BigDecimal minBalance ,@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size",defaultValue = "10") int size){
        System.out.println("[API LAYER] Searching for accounts > + $" + minBalance + " | Page:" + page);

        Page<BankAccount> accountsPage = transferService.getHighValueAccounts(minBalance, page, size);
        return accountsPage.map(acc -> new AccountResponse(acc.getAccountId(), acc.getBalance()));

    }
}
