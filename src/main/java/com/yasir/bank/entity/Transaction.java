package com.yasir.bank.entity;

import com.yasir.bank.entity.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction",
    uniqueConstraints = @UniqueConstraint(columnNames = {"transaction_id"}))
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transaction_id", nullable = false)
  private long transactionId;
  @Column(name = "transaction_amount")
  private BigDecimal transactionAmount;
  @Column(name = "transaction_reference")
  private String transactionReference;
  @Column(name = "transaction_type")
  @Enumerated(EnumType.STRING)
  private TransactionType transactionType;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id")
  private Account account;
  @Column(name = "saved_by")
  protected String savedBy;
  @Column(name = "saved_time")
  protected Long savedTime;

  public long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(long transactionId) {
    this.transactionId = transactionId;
  }

  public BigDecimal getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(BigDecimal transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  public String getTransactionReference() {
    return transactionReference;
  }

  public void setTransactionReference(String transactionReference) {
    this.transactionReference = transactionReference;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public String getSavedBy() {
    return savedBy;
  }

  public void setSavedBy(String savedBy) {
    this.savedBy = savedBy;
  }

  public Long getSavedTime() {
    return savedTime;
  }

  public void setSavedTime(Long savedTime) {
    this.savedTime = savedTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof Transaction that)) return false;

    return new EqualsBuilder().append(getTransactionId(), that.getTransactionId()).append(getTransactionAmount(), that.getTransactionAmount()).append(getTransactionReference(), that.getTransactionReference()).append(getTransactionType(), that.getTransactionType()).append(getAccount(), that.getAccount()).append(getSavedBy(), that.getSavedBy()).append(getSavedTime(), that.getSavedTime()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(getTransactionId()).append(getTransactionAmount()).append(getTransactionReference()).append(getTransactionType()).append(getAccount()).append(getSavedBy()).append(getSavedTime()).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("transactionId", transactionId)
        .append("transactionAmount", transactionAmount)
        .append("transactionReference", transactionReference)
        .append("transactionType", transactionType)
        .append("account", account)
        .append("savedBy", savedBy)
        .append("savedTime", savedTime)
        .toString();
  }
}
