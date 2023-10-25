package com.yasir.bank.service;

import com.yasir.bank.dto.CustomerDTO;
import com.yasir.bank.entity.Customer;

import java.util.List;

public interface CustomerService {

  List<CustomerDTO> getAllCustomers();

}
