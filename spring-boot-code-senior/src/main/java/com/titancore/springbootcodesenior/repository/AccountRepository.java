package com.titancore.springbootcodesenior.repository;


import com.titancore.springbootcodesenior.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // Spring Boot នឹងសរសេរ SQL ឲ្យយើងដោយស្វ័យប្រវត្តិ!
}