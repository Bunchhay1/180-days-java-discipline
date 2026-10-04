package com.titancore.titanbankapi.repository;


import com.titancore.titanbankapi.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String>{

    List<BankAccount> findByBalanceGreaterThan(BigDecimal amount);


}
