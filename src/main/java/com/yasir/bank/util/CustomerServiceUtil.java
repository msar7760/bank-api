package com.yasir.bank.util;

import com.yasir.bank.dto.CustomerDTO;
import com.yasir.bank.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceUtil {

  public static List<CustomerDTO> createCustomerDto(List<Customer> customer) {
    List<CustomerDTO> customerDTOList = new ArrayList<>();
    customer.forEach(customer1 -> {
      if (customer1 != null && customer1.getFirstName() != null && customer1.getLastName() != null) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer1.getFirstName() + " " + customer1.getLastName());
        customerDTO.setId(customer1.getId());
        customerDTOList.add(customerDTO);
      }
    });
    return customerDTOList;
  }
}
