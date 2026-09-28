package com.example.evcharging.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "analytic_accounts")
public class AnalyticAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String analyticCode;

    private String analyticName;

    private String location;

    private Double plannedBudget;

    private Double actualAmount;

    private Boolean active;

    public AnalyticAccount() {
    }

    public AnalyticAccount(String analyticCode,
                           String analyticName,
                           String location,
                           Double plannedBudget,
                           Double actualAmount,
                           Boolean active) {

        this.analyticCode = analyticCode;
        this.analyticName = analyticName;
        this.location = location;
        this.plannedBudget = plannedBudget;
        this.actualAmount = actualAmount;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnalyticCode() {
        return analyticCode;
    }

    public void setAnalyticCode(String analyticCode) {
        this.analyticCode = analyticCode;
    }

    public String getAnalyticName() {
        return analyticName;
    }

    public void setAnalyticName(String analyticName) {
        this.analyticName = analyticName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPlannedBudget() {
        return plannedBudget;
    }

    public void setPlannedBudget(Double plannedBudget) {
        this.plannedBudget = plannedBudget;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}