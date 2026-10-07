package com.titancore.titanregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // the phone book
public class TitanRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TitanRegistryApplication.class, args);
    }

}
