package com.yasir.bank.service;

import com.yasir.bank.dto.TransactionDTO;
import com.yasir.bank.entity.Transaction;
import com.yasir.bank.service.exception.RecordNotFoundException;

import java.util.List;

public interface TransactionService {

  List<Transaction> createTransaction(TransactionDTO transactionDTO) throws Exception;
  List<Transaction> getTransactionHistory(String accountId) throws RecordNotFoundException;
}
