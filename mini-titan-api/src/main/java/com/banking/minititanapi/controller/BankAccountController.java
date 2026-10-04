package com.banking.minititanapi.controller;

import com.banking.minititanapi.dto.BankAccountResponse;
import com.banking.minititanapi.dto.CreateAccountRequest;
import com.banking.minititanapi.entity.BankAccount;
import com.banking.minititanapi.service.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/accounts")
public class BankAccountController{

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService){
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public ResponseEntity<BankAccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request,
            Principal principal) {
        String currentUserId = (principal != null) ? principal.getName() : "MOCK_USER_ID_001";

        BankAccount savedAccount = bankAccountService.createAccount(
                request.accountNumber(),
                currentUserId
        );

        BankAccountResponse response = new BankAccountResponse(
                savedAccount.getAccountNumber(),
                savedAccount.getBalance(),
                savedAccount.getCreatedAt()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<BankAccountResponse> getBalance(
            @PathVariable String accountNumber,
            Principal principal) {

        String currentUserId = (principal != null ) ? principal.getName() : "MOCK_USER_ID_001";

        BankAccount account = bankAccountService.getAccountBalanceSecured(accountNumber, currentUserId);

        BankAccountResponse response = new BankAccountResponse(
                account.getAccountNumber(),
                account.getBalance(),
                account.getCreatedAt()
        );
        return ResponseEntity.ok(response);
    }


}