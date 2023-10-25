package com.yasir.bank.service;

import com.yasir.bank.dto.BankAccountDTO;
import com.yasir.bank.entity.Account;
import com.yasir.bank.entity.Customer;
import com.yasir.bank.entity.enums.AccountType;
import com.yasir.bank.repository.api.BankAccountRepository;
import com.yasir.bank.repository.api.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
public class BankAccountServiceTest {

  @Autowired
  BankAccountService bankAccountService;

  @Autowired
  BankAccountRepository bankAccountRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Test
  @Transactional
  @DisplayName("Verify new bank account creating service")
  public void whenCreateNewBankAccount_thenSuccess() throws Exception {
    BankAccountDTO accountDTO = new BankAccountDTO();
    accountDTO.setAccountName("arisha barron");
    accountDTO.setAccountType(AccountType.SAVING_ACCOUNT);
    accountDTO.setBankName("Commercial");
    accountDTO.setBranch("Colombo");
    accountDTO.setCustomerId(1L);
    accountDTO.setInitialDepositAmount(BigDecimal.ONE);
    accountDTO.setCountry("Sri Lanka");

    Account account = bankAccountService.saveBankAccount(accountDTO);
    assertEquals("arisha barron", account.getAccountName());
    assertNotNull(account.getAccountId());
  }

  @Test
  @Transactional
  @DisplayName("Verify new bank account creating service")
  public void whenCreateNewBankAccountWithDuplicateInputs_thenThrows() throws Exception {
    Customer customer1 = new Customer();
    customer1.setFirstName("Aa");
    customer1.setLastName("Bb");
    Customer savedCustomer1 = customerRepository.save(customer1);

    Account account1 = new Account();
    account1.setAccountName("arisha barron");
    account1.setAccountId("12345");
    account1.setCustomer(savedCustomer1);
    account1.setInitialDeposit(BigDecimal.TEN);
    account1.setAccountBalance(BigDecimal.TEN);
    account1.setAccountType(AccountType.SAVING_ACCOUNT);
    account1.setBranch("Colombo");
    account1.setCountry("Sri Lanka");
    account1.setBank("Commercial");
    account1.setSavedBy("abc");
    account1.setSavedTime(1L);
    bankAccountRepository.save(account1);

    BankAccountDTO accountDTO = new BankAccountDTO();
    accountDTO.setAccountName("arisha barron");
    accountDTO.setAccountType(AccountType.SAVING_ACCOUNT);
    accountDTO.setBankName("Commercial");
    accountDTO.setBranch("Colombo");
    accountDTO.setCustomerId(savedCustomer1.getId());
    accountDTO.setInitialDepositAmount(BigDecimal.ONE);
    accountDTO.setCountry("Sri Lanka");

    assertThrows(Exception.class, () -> bankAccountService.saveBankAccount(accountDTO));
  }

  @Test
  @Transactional
  @DisplayName("Verify new bank account creating service")
  public void whenRetrieveAccountBalanceForGivenAccount_thenSuccess() throws Exception {
    Customer customer1 = new Customer();
    customer1.setFirstName("Aa");
    customer1.setLastName("Bb");
    Customer savedCustomer1 = customerRepository.save(customer1);

    Account account1 = new Account();
    account1.setAccountName("arisha barron");
    account1.setAccountId("12345");
    account1.setCustomer(savedCustomer1);
    account1.setInitialDeposit(BigDecimal.TEN);
    account1.setAccountBalance(BigDecimal.TEN);
    account1.setAccountType(AccountType.SAVING_ACCOUNT);
    account1.setBranch("Colombo");
    account1.setCountry("Sri Lanka");
    account1.setBank("Commercial");
    account1.setSavedBy("abc");
    account1.setSavedTime(1L);
    bankAccountRepository.save(account1);

    assertEquals(BigDecimal.TEN, bankAccountService.getAccountBalance("12345"));
  }

  @Test
  @Transactional
  @DisplayName("Verify new bank account creating service")
  public void whenRetrieveAccountBalanceForNoneExistingAccount_thenThrows() throws Exception {
    assertThrows(Exception.class, () -> bankAccountService.getAccountBalance("12345"));
  }
}
