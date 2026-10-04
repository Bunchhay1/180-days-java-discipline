package com.banking.minititanapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class MiniTitanApiApplication{

    private static final Logger log = LoggerFactory.getLogger(MiniTitanApiApplication.class);

    public static void main(String[] args){
        SpringApplication.run(MiniTitanApiApplication.class, args);
        log.info("Mini-Titan Banking Service is successfully bootstrapped and running!");

    }
}