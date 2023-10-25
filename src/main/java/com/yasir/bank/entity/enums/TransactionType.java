package com.yasir.bank.entity.enums;

import io.swagger.annotations.ApiModel;

@ApiModel
public enum TransactionType {
  DEPOSIT("deposit"),
  WITHDRAW("withdraw");

  private String value;

  TransactionType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
