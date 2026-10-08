package com.titancore.springbootcodesenior.controller;

import com.titancore.springbootcodesenior.dto.Response.ApiResponse;
import com.titancore.springbootcodesenior.dto.Request.TransferRequest;
import com.titancore.springbootcodesenior.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private PaymentService paymentService;

    // dependency inject ( IoC)
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // use POST
    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse> transfer(@RequestBody TransferRequest transferRequest){

        // controller requesting to service -> response back
        String transactionResult = paymentService.processEnterpriseTransfer(transferRequest);

        ApiResponse<String> response = new ApiResponse<>(true , "Transfer Processd", transactionResult);
        return ResponseEntity.ok(response);
    }
}
