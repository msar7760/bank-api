package com.yasir.bank.dto;

import com.yasir.bank.entity.enums.TransactionType;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class TransactionDTO {
  @ApiModelProperty(example = "5bf22ab5-7d84-4624-83da-406db62457a4")
  @NotNull(message = "sender account id can not be blank")
  private String senderAccountId;
  @ApiModelProperty(example = "93ab7741-cc89-4c12-b455-c69ced124af0")
  @NotNull(message = "receiver account Id can not be blank")
  private BigDecimal amount;
  @ApiModelProperty(example = "arisha barron")
  @NotNull(message = "receiver account name id can not be blank")
  private String receiverAccountId;
  @ApiModelProperty(example = "this is for any other transactions")
  @NotNull(message = "sender reference id can not be blank")
  private String senderReference;
  @ApiModelProperty(example = "transactionType")
  @NotNull(message = "transactionType value can not be blank")
  private TransactionType transactionType;

  public String getSenderAccountId() {
    return senderAccountId;
  }

  public void setSenderAccountId(String senderAccountId) {
    this.senderAccountId = senderAccountId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getReceiverAccountId() {
    return receiverAccountId;
  }

  public void setReceiverAccountId(String receiverAccountId) {
    this.receiverAccountId = receiverAccountId;
  }

  public String getSenderReference() {
    return senderReference;
  }

  public void setSenderReference(String senderReference) {
    this.senderReference = senderReference;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof TransactionDTO that)) return false;

    return new EqualsBuilder().append(getSenderAccountId(), that.getSenderAccountId()).append(getAmount(), that.getAmount()).append(getReceiverAccountId(), that.getReceiverAccountId()).append(getSenderReference(), that.getSenderReference()).append(getTransactionType(), that.getTransactionType()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(getSenderAccountId()).append(getAmount()).append(getReceiverAccountId()).append(getSenderReference()).append(getTransactionType()).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("senderAccountId", senderAccountId)
        .append("amount", amount)
        .append("receiverAccountId", receiverAccountId)
        .append("senderReference", senderReference)
        .append("transactionType", transactionType)
        .toString();
  }
}
