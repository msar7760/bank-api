package com.yasir.bank.entity;

import com.yasir.bank.entity.enums.AccountType;
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
@Table(name = "account",
    uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private long id;
  @Column(name = "account_id")
  private String accountId;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  private Customer customer;
  @Column(name = "branch")
  private String branch;
  @Column(name = "country")
  private String country;
  @Column(name = "bank")
  private String bank;
  @Column(name = "account_type")
  @Enumerated(EnumType.STRING)
  private AccountType accountType;
  @Column(name = "account_name")
  private String accountName;
  @Column(name = "initial_deposit")
  private BigDecimal initialDeposit;
  @Column(name = "account_balance")
  private BigDecimal accountBalance = BigDecimal.ZERO;
  @Column(name = "saved_by")
  protected String savedBy;
  @Column(name = "saved_time")
  protected Long savedTime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public BigDecimal getInitialDeposit() {
    return initialDeposit;
  }

  public void setInitialDeposit(BigDecimal initialDeposit) {
    this.initialDeposit = initialDeposit;
  }

  public BigDecimal getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(BigDecimal accountBalance) {
    this.accountBalance = accountBalance;
  }

  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
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

    if (!(o instanceof Account account)) return false;

    return new EqualsBuilder().append(getId(), account.getId()).append(getAccountId(), account.getAccountId()).append(getCustomer(), account.getCustomer()).append(getBranch(), account.getBranch()).append(getCountry(), account.getCountry()).append(getBank(), account.getBank()).append(getAccountType(), account.getAccountType()).append(getAccountName(), account.getAccountName()).append(getInitialDeposit(), account.getInitialDeposit()).append(getAccountBalance(), account.getAccountBalance()).append(getSavedBy(), account.getSavedBy()).append(getSavedTime(), account.getSavedTime()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(getId()).append(getAccountId()).append(getCustomer()).append(getBranch()).append(getCountry()).append(getBank()).append(getAccountType()).append(getAccountName()).append(getInitialDeposit()).append(getAccountBalance()).append(getSavedBy()).append(getSavedTime()).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("accountId", accountId)
        .append("customer", customer)
        .append("branch", branch)
        .append("country", country)
        .append("bank", bank)
        .append("accountType", accountType)
        .append("accountName", accountName)
        .append("initialDeposit", initialDeposit)
        .append("accountBalance", accountBalance)
        .append("savedBy", savedBy)
        .append("savedTime", savedTime)
        .toString();
  }
}
