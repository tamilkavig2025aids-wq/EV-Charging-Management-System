package com.example.evcharging.Controller;

import com.example.evcharging.Dto.BudgetRequest;
import com.example.evcharging.Entity.AnalyticAccount;
import com.example.evcharging.Entity.Budget;
import com.example.evcharging.Service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<Budget> createBudget(
            @RequestBody BudgetRequest request) {

        AnalyticAccount analyticAccount =
                budgetService.getAnalyticAccountById(
                        request.getAnalyticAccountId()
                ).orElseThrow(() ->
                        new RuntimeException("Analytic account not found"));

        Budget budget = new Budget();

        budget.setBudgetName(request.getBudgetName());
        budget.setStartDate(request.getStartDate());
        budget.setEndDate(request.getEndDate());
        budget.setPlannedAmount(request.getPlannedAmount());
        budget.setActualAmount(request.getActualAmount());
        budget.setAnalyticAccount(analyticAccount);

        return ResponseEntity.ok(
                budgetService.save(budget)
        );
    }

    @GetMapping
    public ResponseEntity<List<Budget>> getAllBudgets() {
        return ResponseEntity.ok(
                budgetService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(
            @PathVariable Long id) {

        return budgetService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(
            @PathVariable Long id) {

        budgetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}