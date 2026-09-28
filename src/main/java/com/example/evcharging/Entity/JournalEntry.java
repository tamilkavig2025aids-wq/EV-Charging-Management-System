package com.example.evcharging.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "journal_entries")
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entryNumber;

    private LocalDateTime entryDate;

    private String description;

    private Double totalDebit;

    private Double totalCredit;

    private String status; // DRAFT, POSTED, CANCELLED

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "journal_entry_id")
    private List<JournalEntryLine> lines = new ArrayList<>();

    public JournalEntry() {
    }

    public JournalEntry(String entryNumber,
                        LocalDateTime entryDate,
                        String description,
                        Double totalDebit,
                        Double totalCredit,
                        String status) {

        this.entryNumber = entryNumber;
        this.entryDate = entryDate;
        this.description = description;
        this.totalDebit = totalDebit;
        this.totalCredit = totalCredit;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(String entryNumber) {
        this.entryNumber = entryNumber;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public Double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<JournalEntryLine> getLines() {
        return lines;
    }

    public void setLines(List<JournalEntryLine> lines) {
        this.lines = lines;
    }
}