package com.titancore.titanbankapi.controller;

import com.titancore.titanbankapi.client.NotificationClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/bank")
public class TestTransferController {

    private final NotificationClient notificationClient;

    public TestTransferController(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    // immutability testing readiness
    @GetMapping("/transfer-test")
    public String testTransfer() {
        String bankLogic = "Transfer $500 Success.";
        String smsResponse = notificationClient.sentSms("0123456789", "You transfer $500 Success.");
        return bankLogic + " | " + smsResponse;
    }

}
