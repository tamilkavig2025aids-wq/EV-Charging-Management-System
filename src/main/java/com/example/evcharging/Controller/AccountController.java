package com.example.evcharging.Controller;

import com.example.evcharging.Entity.Account;
import com.example.evcharging.Service.AccountingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountingService accountingService;

    public AccountController(AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(
            @RequestBody Account account) {

        return ResponseEntity.ok(
                accountingService.saveAccount(account)
        );
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(
                accountingService.getAllAccounts()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(
            @PathVariable Long id) {

        return accountingService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(
            @PathVariable Long id) {

        accountingService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}