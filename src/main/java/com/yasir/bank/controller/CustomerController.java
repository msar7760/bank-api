package com.yasir.bank.controller;

import com.yasir.bank.dto.CustomerDTO;
import com.yasir.bank.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bank/v1")
@Validated
public class CustomerController {
  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping(value = "/customer-data")
  @ApiOperation(value = "Retrieve all Customer details",
      notes = "Retrieve all Customer details",
      response = CustomerDTO.class)
  @ApiResponses(value = {
      @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "Retrieve all Customer details success"),
      @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "Retrieve all Customer details failed"),
  })
  public ResponseEntity<List<CustomerDTO>> getCustomerDetails() {
    logger.info("Entering the customer detail retrieve handler");
    List<CustomerDTO> customer = customerService.getAllCustomers();
    return new ResponseEntity<>(customer, HttpStatus.CREATED);
  }
}
