package com.yasir.bank.util;

import com.yasir.bank.dto.TransactionDTO;
import com.yasir.bank.entity.Account;
import com.yasir.bank.entity.Transaction;
import com.yasir.bank.entity.enums.TransactionType;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransactionServiceUtil {

  public static List<Transaction> transactionProcess(Account sender, Account receiver, TransactionDTO transactionDTO) throws Exception {
    /* transaction between two account for any customer*/
    if (transactionDTO.getTransactionType().equals(TransactionType.DEPOSIT) && sender != null && receiver != null) {
      if (sender.getAccountBalance().compareTo(transactionDTO.getAmount()) < 0) {
        String errorData = MessageFormat.format("Bank account balance is not enough sender {0}", sender.getAccountName());
        throw new Exception(errorData);
      }
      sender.setAccountBalance(sender.getAccountBalance().subtract(transactionDTO.getAmount()));
      receiver.setAccountBalance(receiver.getAccountBalance().add(transactionDTO.getAmount()));
      Transaction transaction1 = createNewTransactionEntry(new Transaction(), transactionDTO, sender, TransactionType.WITHDRAW);
      Transaction transaction2 = createNewTransactionEntry(new Transaction(), transactionDTO, receiver, TransactionType.DEPOSIT);

      return List.of(transaction1, transaction2);
    }
    /* transaction for same account for withdrawing money*/
    if (transactionDTO.getTransactionType().equals(TransactionType.WITHDRAW) && sender != null) {
      sender.setAccountBalance(sender.getAccountBalance().subtract(transactionDTO.getAmount()));
      Transaction transaction3 = createNewTransactionEntry(new Transaction(), transactionDTO, sender, TransactionType.WITHDRAW);
      return List.of(transaction3);
    }
    return new ArrayList<>();
  }

  private static Transaction createNewTransactionEntry(Transaction transaction, TransactionDTO transactionDTO, Account receiver, TransactionType type) {
    transaction.setTransactionAmount(transactionDTO.getAmount());
    transaction.setTransactionType(type);
    transaction.setAccount(receiver);
    transaction.setTransactionReference(transactionDTO.getSenderReference());
    transaction.setSavedTime(new Date().getTime());
    transaction.setSavedBy(UUID.randomUUID().toString());
    return transaction;
  }
}
