package com.yasir.bank.util;

import com.yasir.bank.dto.BankAccountDTO;
import com.yasir.bank.entity.Account;
import com.yasir.bank.entity.Customer;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BankAccountServiceUtil {

  public static void validateDuplicateAccounts(BankAccountDTO bankAccountDTO, List<Account> existingAccounts) throws Exception {
    if (existingAccounts.stream().anyMatch(account -> account.getAccountName().equals(bankAccountDTO.getAccountName())
        && account.getAccountType().equals(bankAccountDTO.getAccountType())
        && account.getBank().equals(bankAccountDTO.getBankName()))) {
      String errorData = MessageFormat.format("Bank Account name: `{0}` and type: `{1}` already exists",
          bankAccountDTO.getAccountName(), bankAccountDTO.getAccountType().getValue());
      throw new Exception(errorData);
    }
  }

  public static Account convertDtoToEntity(BankAccountDTO bankAccountDTO, Customer customer) throws Exception {
    Account account = new Account();
    if (bankAccountDTO.getInitialDepositAmount().compareTo(BigDecimal.ZERO) <= 0) {
      String errorData = MessageFormat.format("Bank Initial amount can not be {0}", bankAccountDTO.getInitialDepositAmount());
      throw new Exception(errorData);
    }
    if (bankAccountDTO.getCustomerId() == null || bankAccountDTO.getCustomerId() == 0) {
      String errorData = MessageFormat.format("Bank account customer can not be null or zero for account name {0}", bankAccountDTO.getAccountName());
      throw new Exception(errorData);
    }
    account.setInitialDeposit(bankAccountDTO.getInitialDepositAmount());
    account.setAccountName(bankAccountDTO.getAccountName());
    account.setAccountType(bankAccountDTO.getAccountType());
    account.setBranch(bankAccountDTO.getBranch());
    account.setBank(bankAccountDTO.getBankName());
    account.setCountry(bankAccountDTO.getCountry());
    account.setCustomer(customer);
    account.setAccountBalance(bankAccountDTO.getInitialDepositAmount());
    account.setAccountId(UUID.randomUUID().toString());
    account.setSavedBy(UUID.randomUUID().toString());
    account.setSavedTime(new Date().getTime());
    return account;
  }

  public static BigDecimal getAccountBalance(Account account) {
    if (account != null) {
      return account.getAccountBalance();
    }
    return BigDecimal.ZERO;
  }
}
