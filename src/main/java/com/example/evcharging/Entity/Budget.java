package com.example.evcharging.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String budgetName;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double plannedAmount;

    private Double actualAmount;

    private Double remainingAmount;

    private String status; // OPEN, CLOSED, EXCEEDED

    @ManyToOne
    @JoinColumn(name = "analytic_account_id")
    private AnalyticAccount analyticAccount;

    public Budget() {
    }

    public Budget(String budgetName,
                  LocalDate startDate,
                  LocalDate endDate,
                  Double plannedAmount,
                  Double actualAmount,
                  Double remainingAmount,
                  String status,
                  AnalyticAccount analyticAccount) {

        this.budgetName = budgetName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.plannedAmount = plannedAmount;
        this.actualAmount = actualAmount;
        this.remainingAmount = remainingAmount;
        this.status = status;
        this.analyticAccount = analyticAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getPlannedAmount() {
        return plannedAmount;
    }

    public void setPlannedAmount(Double plannedAmount) {
        this.plannedAmount = plannedAmount;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AnalyticAccount getAnalyticAccount() {
        return analyticAccount;
    }

    public void setAnalyticAccount(AnalyticAccount analyticAccount) {
        this.analyticAccount = analyticAccount;
    }
}