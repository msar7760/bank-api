package com.yasir.bank.controller;

import com.yasir.bank.dto.BankAccountDTO;
import com.yasir.bank.entity.Account;
import com.yasir.bank.service.BankAccountService;
import com.yasir.bank.service.exception.RecordNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bank/v1")
@Validated
public class BankAccountController {
  private static final Logger logger = LoggerFactory.getLogger(BankAccountController.class);
  BankAccountService bankAccountService;

  @Autowired
  public BankAccountController(BankAccountService bankAccountService) {
    this.bankAccountService = bankAccountService;
  }

  @PostMapping("/create-account")
  @ApiOperation(value = "Creates a new Bank Account", notes = "Creates a new Bank Account", response = BankAccountDTO.class)
  @ApiResponses(value = {
      @ApiResponse(code = org.apache.http.HttpStatus.SC_CREATED, message = "Bank Account created"),
      @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "validation failed"),
      @ApiResponse(code = org.apache.http.HttpStatus.SC_NOT_FOUND, message = "not found")
  })
  public ResponseEntity<Account> createBankAccount(@ApiParam(value = "Bank Account details", required = true) @RequestBody BankAccountDTO bankAccountDTO) throws Exception {
    logger.info("Entering the bank account saving handler");
    Account account = bankAccountService.saveBankAccount(bankAccountDTO);
    return new ResponseEntity<>(account, HttpStatus.CREATED);
  }

  @GetMapping(value = "/account-balance")
  @ApiOperation(value = "Retrieve account balance",
      notes = "Retrieve account balance",
      response = BigDecimal.class)
  @ApiResponses(value = {
      @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "Retrieve success"),
      @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "Retrieve failed"),
      @ApiResponse(code = org.apache.http.HttpStatus.SC_NOT_FOUND, message = "not found")
  })
  public ResponseEntity<BigDecimal> getBankBalance(
      @ApiParam(value = "AccountID", required = true)
      @RequestParam(name = "account-id") String accountId) throws RecordNotFoundException {
    logger.info("Entering the bank account retrieve handler");
    BigDecimal balance = bankAccountService.getAccountBalance(accountId);
    return new ResponseEntity<>(balance, HttpStatus.OK);
  }
}
