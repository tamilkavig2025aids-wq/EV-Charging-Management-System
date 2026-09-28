package com.example.evcharging.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountCode;

    private String accountName;

    private String accountType;
    // ASSET, LIABILITY, INCOME, EXPENSE

    private Double balance;

    private Boolean active;

    public Account() {
    }

    public Account(String accountCode,
                   String accountName,
                   String accountType,
                   Double balance,
                   Boolean active) {

        this.accountCode = accountCode;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}