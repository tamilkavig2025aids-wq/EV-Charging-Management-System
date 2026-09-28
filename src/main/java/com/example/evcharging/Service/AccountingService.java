package com.example.evcharging.Service;

import com.example.evcharging.Entity.Account;
import com.example.evcharging.Entity.JournalEntry;
import com.example.evcharging.Entity.JournalEntryLine;
import com.example.evcharging.Repository.AccountRepository;
import com.example.evcharging.Repository.JournalEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountingService {

    private final AccountRepository accountRepository;
    private final JournalEntryRepository journalEntryRepository;

    public AccountingService(
            AccountRepository accountRepository,
            JournalEntryRepository journalEntryRepository) {

        this.accountRepository = accountRepository;
        this.journalEntryRepository = journalEntryRepository;
    }

    // =========================
    // ACCOUNT METHODS
    // =========================

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    // =========================
    // JOURNAL ENTRY METHODS
    // =========================

    public JournalEntry saveJournalEntry(JournalEntry entry) {

        if (entry.getTotalDebit() == null ||
                entry.getTotalCredit() == null) {

            throw new RuntimeException(
                    "Debit and credit amounts are required"
            );
        }

        if (Math.abs(
                entry.getTotalDebit() -
                        entry.getTotalCredit()
        ) > 0.01) {

            throw new RuntimeException(
                    "Journal entry is not balanced. Debit must equal Credit."
            );
        }

        if (entry.getEntryDate() == null) {
            entry.setEntryDate(LocalDateTime.now());
        }

        if (entry.getStatus() == null) {
            entry.setStatus("POSTED");
        }

        return journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(Long id) {
        return journalEntryRepository.findById(id);
    }

    // =========================
    // CHARGING REVENUE ENTRY
    // =========================

    public JournalEntry createChargingRevenueEntry(
            Account cashAccount,
            Account revenueAccount,
            Double amount) {

        if (cashAccount == null ||
                revenueAccount == null) {

            throw new RuntimeException(
                    "Cash account and revenue account are required"
            );
        }

        if (amount == null || amount <= 0) {
            throw new RuntimeException(
                    "Amount must be greater than zero"
            );
        }

        JournalEntry entry = new JournalEntry();

        entry.setEntryNumber(
                "JE-REV-" + System.currentTimeMillis()
        );

        entry.setEntryDate(LocalDateTime.now());

        entry.setDescription(
                "EV Charging Revenue"
        );

        entry.setTotalDebit(amount);
        entry.setTotalCredit(amount);
        entry.setStatus("POSTED");

        List<JournalEntryLine> lines =
                new ArrayList<>();

        // Debit Cash
        JournalEntryLine debitLine =
                new JournalEntryLine();

        debitLine.setEntryType("DEBIT");
        debitLine.setAmount(amount);
        debitLine.setDescription(
                "Charging payment received"
        );
        debitLine.setAccount(cashAccount);

        // Credit Revenue
        JournalEntryLine creditLine =
                new JournalEntryLine();

        creditLine.setEntryType("CREDIT");
        creditLine.setAmount(amount);
        creditLine.setDescription(
                "EV charging revenue"
        );
        creditLine.setAccount(revenueAccount);

        lines.add(debitLine);
        lines.add(creditLine);

        entry.setLines(lines);

        return journalEntryRepository.save(entry);
    }

    // =========================
    // UTILITY COST ENTRY
    // =========================

    public JournalEntry createUtilityCostEntry(
            Account expenseAccount,
            Account creditorAccount,
            Double amount) {

        if (expenseAccount == null ||
                creditorAccount == null) {

            throw new RuntimeException(
                    "Expense account and creditor account are required"
            );
        }

        if (amount == null || amount <= 0) {
            throw new RuntimeException(
                    "Amount must be greater than zero"
            );
        }

        JournalEntry entry = new JournalEntry();

        entry.setEntryNumber(
                "JE-UTIL-" + System.currentTimeMillis()
        );

        entry.setEntryDate(LocalDateTime.now());

        entry.setDescription(
                "Grid Power Utility Cost"
        );

        entry.setTotalDebit(amount);
        entry.setTotalCredit(amount);
        entry.setStatus("POSTED");

        List<JournalEntryLine> lines =
                new ArrayList<>();

        // Debit Utility Expense
        JournalEntryLine debitLine =
                new JournalEntryLine();

        debitLine.setEntryType("DEBIT");
        debitLine.setAmount(amount);
        debitLine.setDescription(
                "Grid electricity expense"
        );
        debitLine.setAccount(expenseAccount);

        // Credit Utility Creditor
        JournalEntryLine creditLine =
                new JournalEntryLine();

        creditLine.setEntryType("CREDIT");
        creditLine.setAmount(amount);
        creditLine.setDescription(
                "Amount payable to utility provider"
        );
        creditLine.setAccount(creditorAccount);

        lines.add(debitLine);
        lines.add(creditLine);

        entry.setLines(lines);

        return journalEntryRepository.save(entry);
    }
}