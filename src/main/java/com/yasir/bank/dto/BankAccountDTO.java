package com.yasir.bank.dto;

import com.yasir.bank.entity.enums.AccountType;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class BankAccountDTO {
  @ApiModelProperty(example = "1")
  @NotNull(message = "customer id can not be blank")
  private Long customerId;
  @ApiModelProperty(example = "Colombo")
  @NotNull(message = "Bank branch can not be blank")
  private String branch;
  @ApiModelProperty(example = "Sri Lanka")
  @NotNull(message = "Country can not be blank")
  private String country;
  @ApiModelProperty(example = "")
  @NotNull(message = "Bank name can not be blank")
  private String bankName;
  @ApiModelProperty(example = "Saving")
  @NotNull(message = "incentive threshold value can not be blank")
  private AccountType accountType;
  @ApiModelProperty(example = "12.3")
  @NotNull(message = "incentive threshold type can not be blank")
  private BigDecimal initialDepositAmount;
  @ApiModelProperty(example = "")
  @NotNull(message = "incentive threshold value can not be blank")
  private String accountName;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public BigDecimal getInitialDepositAmount() {
    return initialDepositAmount;
  }

  public void setInitialDepositAmount(BigDecimal initialDepositAmount) {
    this.initialDepositAmount = initialDepositAmount;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof BankAccountDTO that)) return false;

    return new EqualsBuilder().append(getCustomerId(), that.getCustomerId()).append(getBranch(), that.getBranch()).append(getCountry(), that.getCountry()).append(getBankName(), that.getBankName()).append(getAccountType(), that.getAccountType()).append(getInitialDepositAmount(), that.getInitialDepositAmount()).append(getAccountName(), that.getAccountName()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(getCustomerId()).append(getBranch()).append(getCountry()).append(getBankName()).append(getAccountType()).append(getInitialDepositAmount()).append(getAccountName()).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("customerId", customerId)
        .append("branch", branch)
        .append("country", country)
        .append("bankName", bankName)
        .append("accountType", accountType)
        .append("initialDepositAmount", initialDepositAmount)
        .append("accountName", accountName)
        .toString();
  }
}
