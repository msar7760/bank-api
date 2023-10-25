package com.yasir.bank.repository.api;

import com.yasir.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<Account,Long> {

  Optional<Account> findByAccountId(String accountId);
}
