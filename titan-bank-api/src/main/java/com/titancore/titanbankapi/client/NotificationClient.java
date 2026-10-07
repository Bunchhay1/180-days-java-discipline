package com.titancore.titanbankapi.client;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

// Feign Client connecting to the notification microservice
// following the anti-corruption layer pattern


@FeignClient(
        name = "titan-notification-api",
        path = "/api/notification" // this is path
)
public interface NotificationClient {

    @GetMapping("/sent")
    String sentSms(
            @RequestParam("phone") String phone,
            @RequestParam("message") String message
    );
}
