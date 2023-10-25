package com.yasir.bank.service;

import com.yasir.bank.dto.TransactionDTO;
import com.yasir.bank.entity.Account;
import com.yasir.bank.entity.Customer;
import com.yasir.bank.entity.Transaction;
import com.yasir.bank.entity.enums.AccountType;
import com.yasir.bank.entity.enums.TransactionType;
import com.yasir.bank.repository.api.BankAccountRepository;
import com.yasir.bank.repository.api.CustomerRepository;
import com.yasir.bank.service.exception.RecordNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
public class TransactionServiceTest {

  @Autowired
  BankAccountRepository bankAccountRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  TransactionService transactionService;

  @Test
  @Transactional
  @DisplayName("Verify creating new transaction")
  public void whenCreateNewTransactionBetweenTwoCustomers_thenSuccess() throws Exception {
    Customer customer1 = new Customer();
    Customer customer2 = new Customer();

    customer1.setFirstName("Aa");
    customer1.setLastName("Bb");

    customer2.setFirstName("Cc");
    customer2.setLastName("Bb");

    Customer savedCustomer1 = customerRepository.save(customer1);
    Customer savedCustomer2 = customerRepository.save(customer2);

    Account account1 = new Account();
    Account account2 = new Account();

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

    account2.setAccountName("arisha");
    account2.setAccountId("34526");
    account2.setCustomer(savedCustomer2);
    account2.setInitialDeposit(BigDecimal.TEN);
    account2.setAccountBalance(BigDecimal.TEN);
    account2.setAccountType(AccountType.SAVING_ACCOUNT);
    account2.setBranch("Colombo");
    account2.setCountry("Sri Lanka");
    account2.setBank("Commercial");
    account2.setSavedBy("cdb");
    account2.setSavedTime(2L);

    Account savedAccount1 = bankAccountRepository.save(account1);
    Account savedAccount2 = bankAccountRepository.save(account2);

    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setSenderReference("this is any other transaction");
    transactionDTO.setTransactionType(TransactionType.DEPOSIT);
    transactionDTO.setAmount(BigDecimal.ONE);
    transactionDTO.setReceiverAccountId(savedAccount2.getAccountId());
    transactionDTO.setSenderAccountId(savedAccount1.getAccountId());

    List<Transaction> transactionList = transactionService.createTransaction (transactionDTO);
    Map<TransactionType, Transaction> transactionMap = transactionList.stream().collect(Collectors.toMap(Transaction::getTransactionType, Function.identity()));
    assertEquals(2, transactionList.size());
    assertEquals(BigDecimal.valueOf(11), transactionMap.get(TransactionType.DEPOSIT).getAccount().getAccountBalance());
    assertEquals(BigDecimal.valueOf(9), transactionMap.get(TransactionType.WITHDRAW).getAccount().getAccountBalance());
  }

  @Test
  @Transactional
  @DisplayName("Verify creating new transaction")
  public void whenCreateNewTransactionWithSameCustomerAccounts_thenSuccess() throws Exception {
    Customer customer1 = new Customer();
    Customer customer2 = new Customer();

    customer1.setFirstName("Aa");
    customer1.setLastName("Bb");

    customer2.setFirstName("Aa");
    customer2.setLastName("Bb");

    Customer savedCustomer1 = customerRepository.save(customer1);
    Customer savedCustomer2 = customerRepository.save(customer2);

    Account account1 = new Account();
    Account account2 = new Account();

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

    account2.setAccountName("arisha");
    account2.setAccountId("34526");
    account2.setCustomer(savedCustomer2);
    account2.setInitialDeposit(BigDecimal.TEN);
    account2.setAccountBalance(BigDecimal.TEN);
    account2.setAccountType(AccountType.CURRENT_ACCOUNT);
    account2.setBranch("Matara");
    account2.setCountry("Sri Lanka");
    account2.setBank("Commercial");
    account2.setSavedBy("cdb");
    account2.setSavedTime(2L);

    Account savedAccount1 = bankAccountRepository.save(account1);
    Account savedAccount2 = bankAccountRepository.save(account2);

    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setSenderReference("this is any other transaction");
    transactionDTO.setTransactionType(TransactionType.DEPOSIT);
    transactionDTO.setAmount(BigDecimal.ONE);
    transactionDTO.setReceiverAccountId(savedAccount2.getAccountId());
    transactionDTO.setSenderAccountId(savedAccount1.getAccountId());

    List<Transaction> transactionList = transactionService.createTransaction (transactionDTO);
    Map<TransactionType, Transaction> transactionMap = transactionList.stream().collect(Collectors.toMap(Transaction::getTransactionType, Function.identity()));
    assertEquals(2, transactionList.size());
    assertEquals(BigDecimal.valueOf(11), transactionMap.get(TransactionType.DEPOSIT).getAccount().getAccountBalance());
    assertEquals(BigDecimal.valueOf(9), transactionMap.get(TransactionType.WITHDRAW).getAccount().getAccountBalance());
  }


  @Test
  @Transactional
  @DisplayName("Verify creating new transaction")
  public void whenCreateNewTransactionWithSameCustomerAccountsWithWithdrawal_thenSuccess() throws Exception {
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
    Account savedAccount1 = bankAccountRepository.save(account1);

    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setSenderReference("this is any other transaction");
    transactionDTO.setTransactionType(TransactionType.WITHDRAW);
    transactionDTO.setAmount(BigDecimal.ONE);
    transactionDTO.setReceiverAccountId("");
    transactionDTO.setSenderAccountId(savedAccount1.getAccountId());

    List<Transaction> transactionList = transactionService.createTransaction (transactionDTO);
    assertEquals(1, transactionList.size());
    assertEquals(BigDecimal.valueOf(9), transactionList.get(0).getAccount().getAccountBalance());
  }

  @Test
  @Transactional
  @DisplayName("Verify creating new transaction")
  public void whenCreateNewTransactionWithNotEnoughBalance_thenThrows() throws Exception {
    Customer customer1 = new Customer();
    Customer customer2 = new Customer();

    customer1.setFirstName("Aa");
    customer1.setLastName("Bb");

    customer2.setFirstName("Aa");
    customer2.setLastName("Cc");

    Customer savedCustomer1 = customerRepository.save(customer1);
    Customer savedCustomer2 = customerRepository.save(customer2);

    Account account1 = new Account();
    Account account2 = new Account();

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

    account2.setAccountName("arisha");
    account2.setAccountId("34526");
    account2.setCustomer(savedCustomer2);
    account2.setInitialDeposit(BigDecimal.TEN);
    account2.setAccountBalance(BigDecimal.TEN);
    account2.setAccountType(AccountType.CURRENT_ACCOUNT);
    account2.setBranch("Matara");
    account2.setCountry("Sri Lanka");
    account2.setBank("Commercial");
    account2.setSavedBy("cdb");
    account2.setSavedTime(2L);

    Account savedAccount1 = bankAccountRepository.save(account1);
    Account savedAccount2 = bankAccountRepository.save(account2);

    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setSenderReference("this is any other transaction");
    transactionDTO.setTransactionType(TransactionType.DEPOSIT);
    transactionDTO.setAmount(BigDecimal.valueOf(12));
    transactionDTO.setReceiverAccountId(savedAccount2.getAccountId());
    transactionDTO.setSenderAccountId(savedAccount1.getAccountId());

   assertThrows(Exception.class, ()-> transactionService.createTransaction (transactionDTO));
  }

  @Test
  @Transactional
  @DisplayName("Verify creating new transaction")
  public void whenRetrieveTransactionHistoryForGivenAccount_thenSuccess() throws Exception {
    Customer customer1 = new Customer();
    Customer customer2 = new Customer();

    customer1.setFirstName("Aa");
    customer1.setLastName("Bb");

    customer2.setFirstName("Aa");
    customer2.setLastName("Cc");

    Customer savedCustomer1 = customerRepository.save(customer1);
    Customer savedCustomer2 = customerRepository.save(customer2);

    Account account1 = new Account();
    Account account2 = new Account();

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

    account2.setAccountName("arisha");
    account2.setAccountId("34526");
    account2.setCustomer(savedCustomer2);
    account2.setInitialDeposit(BigDecimal.TEN);
    account2.setAccountBalance(BigDecimal.TEN);
    account2.setAccountType(AccountType.CURRENT_ACCOUNT);
    account2.setBranch("Matara");
    account2.setCountry("Sri Lanka");
    account2.setBank("Commercial");
    account2.setSavedBy("cdb");
    account2.setSavedTime(2L);
    Account savedAccount1 = bankAccountRepository.save(account1);
    Account savedAccount2 = bankAccountRepository.save(account2);

    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setSenderReference("this is any other transaction");
    transactionDTO.setTransactionType(TransactionType.DEPOSIT);
    transactionDTO.setAmount(BigDecimal.valueOf(1));
    transactionDTO.setReceiverAccountId(savedAccount2.getAccountId());
    transactionDTO.setSenderAccountId(savedAccount1.getAccountId());
    transactionService.createTransaction (transactionDTO);

    List<Transaction> transactionList = transactionService.getTransactionHistory("34526");

    assertEquals(1, transactionList.size());
    assertEquals("arisha", transactionList.get(0).getAccount().getAccountName());
    assertEquals(TransactionType.DEPOSIT, transactionList.get(0).getTransactionType());
  }

  @Test
  @Transactional
  @DisplayName("Verify creating new transaction")
  public void whenRetrieveTransactionHistoryForNotExistingAccount_thenTrows() throws Exception {
    assertThrows(RecordNotFoundException.class , () ->transactionService.getTransactionHistory("34526"));
  }
}
