package com.yasir.bank.service;

import com.yasir.bank.dto.TransactionDTO;
import com.yasir.bank.entity.Account;
import com.yasir.bank.entity.Transaction;
import com.yasir.bank.entity.enums.TransactionType;
import com.yasir.bank.repository.api.BankAccountRepository;
import com.yasir.bank.repository.api.TransactionRepository;
import com.yasir.bank.service.exception.RecordNotFoundException;
import com.yasir.bank.util.TransactionServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
  private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

  @Autowired
  BankAccountRepository bankAccountRepository;
  @Autowired
  TransactionRepository transactionRepository;

  @Override
  public List<Transaction> createTransaction(TransactionDTO transactionDTO) throws Exception {
    List<Transaction> transactionList;
    if (transactionDTO.getTransactionType().equals(TransactionType.DEPOSIT)) {
      Account senderAccount = bankAccountRepository.findByAccountId(transactionDTO.getSenderAccountId()).orElse(null);
      Account receiverAccount = bankAccountRepository.findByAccountId(transactionDTO.getReceiverAccountId()).orElse(null);
      transactionList = TransactionServiceUtil.transactionProcess(senderAccount, receiverAccount, transactionDTO);
    } else {
      Account senderAccount = bankAccountRepository.findByAccountId(transactionDTO.getSenderAccountId()).orElse(null);
      transactionList = TransactionServiceUtil.transactionProcess(senderAccount, null, transactionDTO);
    }
    logger.debug("successfully converted dto to transaction entity {} for transaction reference", transactionDTO.getSenderReference());
    return transactionRepository.saveAll(transactionList);
  }

  @Override
  public List<Transaction> getTransactionHistory(String accountId) throws RecordNotFoundException {
    Account selectedAccount = bankAccountRepository.findByAccountId(accountId).orElseThrow(() -> new RecordNotFoundException(
        MessageFormat.format("account id: {0} does not have records", accountId)));
    return transactionRepository.findByAccount_Id(selectedAccount.getId());
  }
}
