package com.yasir.bank.entity.enums;

import io.swagger.annotations.ApiModel;

@ApiModel
public enum AccountType {
  SAVING_ACCOUNT("Savings"),
  CURRENT_ACCOUNT("Current Account");

  private String value;

  AccountType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
