package com.yasir.bank.service;

import com.yasir.bank.dto.BankAccountDTO;
import com.yasir.bank.entity.Account;
import com.yasir.bank.entity.Customer;
import com.yasir.bank.repository.api.BankAccountRepository;
import com.yasir.bank.repository.api.CustomerRepository;
import com.yasir.bank.service.exception.RecordNotFoundException;
import com.yasir.bank.util.BankAccountServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {
  private static final Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);

  @Autowired
  BankAccountRepository bankAccountRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Override
  public Account saveBankAccount(BankAccountDTO bankAccountDTO) throws Exception {
    List<Account> existingAccounts = bankAccountRepository.findAll();
    Customer customer = customerRepository.findAllById(bankAccountDTO.getCustomerId());
    BankAccountServiceUtil.validateDuplicateAccounts(bankAccountDTO, existingAccounts);
    Account account = BankAccountServiceUtil.convertDtoToEntity(bankAccountDTO, customer);
    logger.debug("successfully converted dto to bank account entity {} for account name", account.getAccountName());
    return bankAccountRepository.save(account);
  }

  @Override
  public BigDecimal getAccountBalance(String accountId) throws RecordNotFoundException {
    Account account = bankAccountRepository.findByAccountId(accountId).orElseThrow(() -> new RecordNotFoundException(
        MessageFormat.format("account id: {0} does not have records", accountId)));
    return BankAccountServiceUtil.getAccountBalance(account);
  }
}
