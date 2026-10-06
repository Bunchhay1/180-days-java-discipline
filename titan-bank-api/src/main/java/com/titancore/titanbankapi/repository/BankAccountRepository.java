package com.titancore.titanbankapi.repository;


import com.titancore.titanbankapi.domain.BankAccount;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Lock;
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String>{

    Page<BankAccount> findByBalanceGreaterThan(BigDecimal amount, Pageable pageable);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM BankAccount b WHERE b.accountId = :accountId")
    Optional<BankAccount> findByIdForUpdate(@Param("accountId") String accountId);


}
