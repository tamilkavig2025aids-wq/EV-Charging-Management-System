package com.example.evcharging.Controller;

import com.example.evcharging.Dto.JournalEntryRequest;
import com.example.evcharging.Entity.JournalEntry;
import com.example.evcharging.Service.AccountingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/journal-entries")
public class JournalEntryController {

    private final AccountingService accountingService;

    public JournalEntryController(AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createJournalEntry(
            @RequestBody JournalEntryRequest request) {

        JournalEntry entry = new JournalEntry();

        entry.setEntryNumber(request.getEntryNumber());

        // JournalEntry uses LocalDateTime
        entry.setEntryDate(LocalDateTime.now());

        entry.setDescription(request.getDescription());
        entry.setTotalDebit(request.getTotalDebit());
        entry.setTotalCredit(request.getTotalCredit());
        entry.setStatus("POSTED");

        return ResponseEntity.ok(
                accountingService.saveJournalEntry(entry)
        );
    }

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntries() {

        return ResponseEntity.ok(
                accountingService.getAllJournalEntries()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(
            @PathVariable Long id) {

        return accountingService.getJournalEntryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}