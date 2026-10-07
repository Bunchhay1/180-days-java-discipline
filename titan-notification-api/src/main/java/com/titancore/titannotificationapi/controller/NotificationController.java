package com.titancore.titannotificationapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;



@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private static final Logger log = Logger.getLogger(NotificationController.class.getName());

    // this api scan before connection bank api
    @GetMapping("/send")
    public String sendSms(@RequestParam String phone, @RequestParam String message){
        log.info("[SMS SERVER] Sending message to " + phone + " : " + message);
        return "SMS successfully queued for " + phone;
    }
}
