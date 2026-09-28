package com.example.evcharging.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "journal_entry_lines")
public class JournalEntryLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entryType; // DEBIT or CREDIT

    private Double amount;

    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public JournalEntryLine() {
    }

    public JournalEntryLine(String entryType,
                            Double amount,
                            String description,
                            Account account) {

        this.entryType = entryType;
        this.amount = amount;
        this.description = description;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}