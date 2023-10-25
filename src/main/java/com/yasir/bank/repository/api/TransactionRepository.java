package com.yasir.bank.repository.api;

import com.yasir.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  List<Transaction> findByAccount_Id(Long id);
}
