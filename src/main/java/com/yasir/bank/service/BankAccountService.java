package com.yasir.bank.service;

import com.yasir.bank.dto.BankAccountDTO;
import com.yasir.bank.entity.Account;
import com.yasir.bank.service.exception.RecordNotFoundException;

import java.math.BigDecimal;

public interface BankAccountService {
  Account saveBankAccount(BankAccountDTO bankAccountDTO) throws Exception;

  BigDecimal getAccountBalance(String accountId) throws RecordNotFoundException;
}
