package com.yasir.bank.util;

import com.yasir.bank.dto.TransactionDTO;
import com.yasir.bank.entity.Account;
import com.yasir.bank.entity.Transaction;
import com.yasir.bank.entity.enums.TransactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class TransactionServiceUtilTest {

  @Test
  @DisplayName("Convert transaction dto to entity")
  void whenTransactionProcessWithNullSenderOrReceiver_thenThrows() throws Exception {
    TransactionDTO transactionDTO = new TransactionDTO();

    Account sender1 = new Account();
    Account receiver1 = null;

    Account sender2 = null;
    Account receiver2 = null;

    Account sender3 = null;
    Account receiver3 = null;

    assertThrows(Exception.class, () -> TransactionServiceUtil.transactionProcess(sender1, receiver1, transactionDTO));
    assertThrows(Exception.class, () -> TransactionServiceUtil.transactionProcess(sender2, receiver2, transactionDTO));
    assertThrows(Exception.class, () -> TransactionServiceUtil.transactionProcess(sender3, receiver3, transactionDTO));
  }

  @Test
  @DisplayName("Convert transaction dto to entity")
  void whenTransactionProcessWithValidDataAsDeposit_thenSuccess() throws Exception {
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setAmount(BigDecimal.ONE);
    transactionDTO.setTransactionType(TransactionType.DEPOSIT);
    transactionDTO.setSenderReference("this is any other transaction");
    transactionDTO.setSenderAccountId("1234234");
    transactionDTO.setReceiverAccountId("2414313");

    Account sender1 = new Account();
    sender1.setAccountBalance(BigDecimal.TEN);
    Account receiver1 = new Account();
    receiver1.setAccountBalance(BigDecimal.ZERO);


    List<Transaction> transactionList = TransactionServiceUtil.transactionProcess(sender1, receiver1, transactionDTO);
    assertEquals(2, transactionList.size());
  }

  @Test
  @DisplayName("Convert transaction dto to entity")
  void whenTransactionProcessWithValidDataAsWithdrawal_thenSuccess() throws Exception {
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setAmount(BigDecimal.ONE);
    transactionDTO.setTransactionType(TransactionType.WITHDRAW);
    transactionDTO.setSenderReference("this is any other transaction");
    transactionDTO.setSenderAccountId("1234234");
    transactionDTO.setReceiverAccountId("");

    Account sender1 = new Account();
    sender1.setAccountBalance(BigDecimal.TEN);

    List<Transaction> transactionList = TransactionServiceUtil.transactionProcess(sender1, null, transactionDTO);
    assertEquals(1, transactionList.size());
  }
}
