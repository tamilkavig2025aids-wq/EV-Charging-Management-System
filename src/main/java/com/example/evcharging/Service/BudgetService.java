package com.example.evcharging.Service;

import com.example.evcharging.Entity.AnalyticAccount;
import com.example.evcharging.Entity.Budget;
import com.example.evcharging.Repository.AnalyticAccountRepository;
import com.example.evcharging.Repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final AnalyticAccountRepository analyticAccountRepository;

    public BudgetService(
            BudgetRepository budgetRepository,
            AnalyticAccountRepository analyticAccountRepository) {

        this.budgetRepository = budgetRepository;
        this.analyticAccountRepository = analyticAccountRepository;
    }

    // =========================
    // BUDGET METHODS
    // =========================

    public Budget save(Budget budget) {

        calculateBudget(budget);

        return budgetRepository.save(budget);
    }

    public List<Budget> getAll() {
        return budgetRepository.findAll();
    }

    public Optional<Budget> getById(Long id) {
        return budgetRepository.findById(id);
    }

    public void delete(Long id) {
        budgetRepository.deleteById(id);
    }

    // =========================
    // ANALYTIC ACCOUNT METHODS
    // =========================

    public AnalyticAccount saveAnalyticAccount(
            AnalyticAccount account) {

        return analyticAccountRepository.save(account);
    }

    public List<AnalyticAccount> getAllAnalyticAccounts() {

        return analyticAccountRepository.findAll();
    }

    public Optional<AnalyticAccount> getAnalyticAccountById(
            Long id) {

        return analyticAccountRepository.findById(id);
    }

    public void deleteAnalyticAccount(Long id) {

        analyticAccountRepository.deleteById(id);
    }

    // =========================
    // BUDGET CALCULATION
    // =========================

    public void calculateBudget(Budget budget) {

        double planned =
                budget.getPlannedAmount() != null
                        ? budget.getPlannedAmount()
                        : 0.0;

        double actual =
                budget.getActualAmount() != null
                        ? budget.getActualAmount()
                        : 0.0;

        double remaining = planned - actual;

        budget.setRemainingAmount(remaining);

        if (actual > planned) {
            budget.setStatus("EXCEEDED");
        } else {
            budget.setStatus("OPEN");
        }
    }
}