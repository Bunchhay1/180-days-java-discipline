package com.titancore.springbootcodesenior.config;


import com.titancore.springbootcodesenior.entity.Account;
import com.titancore.springbootcodesenior.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder {

    private static final Logger log = LoggerFactory.getLogger(DatabaseSeeder.class);

    @Bean
    CommandLineRunner initDatabase(AccountRepository repository) {
        return args -> {
            log.info("Starting Database Injection...");

            repository.save(new Account("ACC-123", 5000.0));
            repository.save(new Account("ACC-456", 0.0));

            log.info("Mock Accounts loaded into H2 successfully!");
        };
    }
}