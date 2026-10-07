// បន្ទាត់ទី ១ នេះត្រូវតែដូចគ្នាបេះបិទទៅនឹងឈ្មោះ Folder របស់អ្នក!
package com.titancore.titanbankapi;

import com.titancore.titanbankapi.domain.BankAccount;
import com.titancore.titanbankapi.repository.BankAccountRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.titancore.titanbankapi.service.FundsTransferService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;


@EnableJpaAuditing
@SpringBootApplication
@EnableFeignClients // it is for manager call api
public class TitanBankApiApplication implements CommandLineRunner {

    private final BankAccountRepository repository;
    private final FundsTransferService transferService;
    public TitanBankApiApplication(BankAccountRepository repository, FundsTransferService transferService){
        this.repository = repository;
        this.transferService = transferService;
    }
    public static void main(String[] args){
        SpringApplication.run(TitanBankApiApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception{
        System.out.println("\n--- [SYSTEM BOOT] Setup database --");

        repository.save(new BankAccount("ACC-001", new BigDecimal("5000.00")));
        repository.save(new BankAccount("ACC-002", new BigDecimal("0.00")));
        System.out.println("[DB LAYER] Register Successful ");

        transferService.transfer("ACC-001", "ACC-002", new BigDecimal("1500"));
        System.out.println("\n----[FINAL AUDIT REPORT (data report last time)] ");
        System.out.println("\n ACC-001: $" + repository.findById("ACC-001").get().getBalance());
        System.out.println("\n ACC-002: $" + repository.findById("ACC-002").get().getBalance());
        System.out.println("-----------------------------------------\n");
    }
}