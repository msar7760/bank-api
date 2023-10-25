package com.yasir.bank.util;

import com.yasir.bank.dto.CustomerDTO;
import com.yasir.bank.entity.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class CustomerServiceUtilTest {

  @Test
  @DisplayName("Convert customer names string to full name")
  void whenConvertToDtoWithNewName_thenSuccess() {
    Customer customer = new Customer();
    customer.setFirstName("arish");
    customer.setLastName("barron");
    customer.setId(1);
    List<Customer> customerList = List.of(customer);

    List<CustomerDTO> customerDTOList = CustomerServiceUtil.createCustomerDto(customerList);
    assertEquals("arish barron", customerDTOList.get(0).getName());
  }
}
