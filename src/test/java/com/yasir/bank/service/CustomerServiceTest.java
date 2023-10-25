package com.yasir.bank.service;

import com.yasir.bank.dto.CustomerDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
public class CustomerServiceTest {

  @Autowired
  CustomerServiceImpl customerService;

  @Test
  @Transactional
  @DisplayName("Verify retrieval of customer details")
  public void whenRetrieveAllCustomerData_thenSuccess() {
    List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
    assertEquals(4, customerDTOList.size());
  }
}

