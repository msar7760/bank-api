package com.yasir.bank.repository.api;

import com.yasir.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Customer findAllById(Long id);
}
