package com.example.evcharging.Controller;

import com.example.evcharging.Entity.AnalyticAccount;
import com.example.evcharging.Service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytic-accounts")
public class AnalyticAccountController {

    private final BudgetService budgetService;

    public AnalyticAccountController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<AnalyticAccount> createAnalyticAccount(
            @RequestBody AnalyticAccount account) {

        return ResponseEntity.ok(
                budgetService.saveAnalyticAccount(account)
        );
    }

    @GetMapping
    public ResponseEntity<List<AnalyticAccount>> getAllAnalyticAccounts() {
        return ResponseEntity.ok(
                budgetService.getAllAnalyticAccounts()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalyticAccount> getAnalyticAccountById(
            @PathVariable Long id) {

        return budgetService.getAnalyticAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalyticAccount(
            @PathVariable Long id) {

        budgetService.deleteAnalyticAccount(id);
        return ResponseEntity.noContent().build();
    }
}