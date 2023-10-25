package com.yasir.bank.util;

import com.yasir.bank.dto.BankAccountDTO;
import com.yasir.bank.entity.Account;
import com.yasir.bank.entity.Customer;
import com.yasir.bank.entity.enums.AccountType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class BankAccountServiceUtilTest {

  @Test
  @DisplayName("Convert bank account details to entity")
  void whenConvertDTOtoEntityWithZeroValue_thenThrows() throws Exception {
    Customer customer = new Customer();
    customer.setFirstName("arish");
    customer.setLastName("barron");
    customer.setId(1);

    BankAccountDTO bankAccountDTO = new BankAccountDTO();
    bankAccountDTO.setAccountName("Account A");
    bankAccountDTO.setAccountType(AccountType.SAVING_ACCOUNT);
    bankAccountDTO.setBankName("Commercial");
    bankAccountDTO.setCountry("Sri Lanka");
    bankAccountDTO.setBranch("Walasmulla");
    bankAccountDTO.setInitialDepositAmount(BigDecimal.ZERO);
    bankAccountDTO.setCustomerId(1L);

    assertThrows(Exception.class, () -> BankAccountServiceUtil.convertDtoToEntity(bankAccountDTO, customer));
  }

  @Test
  @DisplayName("Convert bank account details to entity")
  void whenConvertDTOtoEntityWithZeroOrNullCustomerIdValue_thenThrows() throws Exception {
    BankAccountDTO bankAccountDTO1 = new BankAccountDTO();
    bankAccountDTO1.setAccountName("Account A");
    bankAccountDTO1.setAccountType(AccountType.SAVING_ACCOUNT);
    bankAccountDTO1.setBankName("Commercial");
    bankAccountDTO1.setCountry("Sri Lanka");
    bankAccountDTO1.setBranch("Walasmulla");
    bankAccountDTO1.setInitialDepositAmount(BigDecimal.TEN);
    bankAccountDTO1.setCustomerId(null);

    BankAccountDTO bankAccountDTO2 = new BankAccountDTO();
    bankAccountDTO2.setAccountName("Account A");
    bankAccountDTO2.setAccountType(AccountType.SAVING_ACCOUNT);
    bankAccountDTO2.setBankName("Commercial");
    bankAccountDTO2.setCountry("Sri Lanka");
    bankAccountDTO2.setBranch("Walasmulla");
    bankAccountDTO2.setInitialDepositAmount(BigDecimal.TEN);
    bankAccountDTO2.setCustomerId(0L);

    assertThrows(Exception.class, () -> BankAccountServiceUtil.convertDtoToEntity(bankAccountDTO1, new Customer()));
    assertThrows(Exception.class, () -> BankAccountServiceUtil.convertDtoToEntity(bankAccountDTO2, new Customer()));
  }

  @Test
  @DisplayName("Convert bank account details to entity")
  void whenConvertDTOtoEntity_thenSuccess() throws Exception {
    Customer customer = new Customer();
    customer.setFirstName("arish");
    customer.setLastName("barron");
    customer.setId(1L);

    BankAccountDTO bankAccountDTO = new BankAccountDTO();
    bankAccountDTO.setAccountName("Account A");
    bankAccountDTO.setAccountType(AccountType.SAVING_ACCOUNT);
    bankAccountDTO.setBankName("Commercial");
    bankAccountDTO.setCountry("Sri Lanka");
    bankAccountDTO.setBranch("Walasmulla");
    bankAccountDTO.setInitialDepositAmount(BigDecimal.TEN);
    bankAccountDTO.setCustomerId(customer.getId());

    Account account = BankAccountServiceUtil.convertDtoToEntity(bankAccountDTO, customer);
    assertEquals("Account A", account.getAccountName());
    assertEquals(BigDecimal.TEN, account.getAccountBalance());
  }
}
