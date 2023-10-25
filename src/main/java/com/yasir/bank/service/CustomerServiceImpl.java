package com.yasir.bank.service;

import com.yasir.bank.dto.CustomerDTO;
import com.yasir.bank.entity.Customer;
import com.yasir.bank.repository.api.CustomerRepository;
import com.yasir.bank.util.CustomerServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
  private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

  @Autowired
  CustomerRepository customerRepository;

  public List<CustomerDTO> getAllCustomers() {
    List<Customer> customerList = customerRepository.findAll();
    logger.debug("successfully retrieved all customer details");
    return CustomerServiceUtil.createCustomerDto(customerList);
  }
}
