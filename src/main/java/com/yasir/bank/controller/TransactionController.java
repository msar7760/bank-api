package com.yasir.bank.controller;

import com.yasir.bank.dto.TransactionDTO;
import com.yasir.bank.entity.Transaction;
import com.yasir.bank.service.TransactionService;
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

import java.util.List;

@RestController
@RequestMapping("/bank/v1")
@Validated
public class TransactionController {
  private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
  private final TransactionService transactionService;

  @Autowired
  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping("/create-transaction")
  @ApiOperation(value = "Creates a new transaction", notes = "Creates a new transaction", response = TransactionDTO.class)
  @ApiResponses(value = {
      @ApiResponse(code = org.apache.http.HttpStatus.SC_CREATED, message = "Transaction created"),
      @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "validation failed"),
      @ApiResponse(code = org.apache.http.HttpStatus.SC_NOT_FOUND, message = "not found")
  })
  public ResponseEntity<List<Transaction>> createNewTransaction(@ApiParam(value = "Transaction details", required = true) @RequestBody TransactionDTO transactionDTO) throws Exception {
    logger.info("Entering the new transaction saving handler");
    List<Transaction> transactionList = transactionService.createTransaction(transactionDTO);
    return new ResponseEntity<>(transactionList, HttpStatus.CREATED);
  }

  @GetMapping(value = "/transaction-history")
  @ApiOperation(value = "Retrieve all transactions details for given account",
      notes = "Retrieve all transactions details for given account",
      response = Transaction.class)
  @ApiResponses(value = {
      @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "Retrieve all transactions success"),
      @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "Retrieve transaction failed"),
      @ApiResponse(code = org.apache.http.HttpStatus.SC_NOT_FOUND, message = "Account not found")
  })
  public ResponseEntity<List<Transaction>> getTransactionHistory(
      @ApiParam(value = "AccountID", required = true)
      @RequestParam(name = "account-id") String accountId
  ) throws RecordNotFoundException {
    logger.info("Entering the transactions retrieve handler");
    List<Transaction> transactionList = transactionService.getTransactionHistory(accountId);
    return new ResponseEntity<>(transactionList, HttpStatus.OK);
  }
}
