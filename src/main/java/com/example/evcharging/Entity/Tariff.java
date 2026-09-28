package com.example.evcharging.Entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "tariffs")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tariffName;

    private Double baseRate;

    private Double multiplier;

    private LocalTime startTime;

    private LocalTime endTime;

    private String tariffType; // PEAK, OFF_PEAK

    private Boolean active;

    public Tariff() {
    }

    public Tariff(String tariffName,
                  Double baseRate,
                  Double multiplier,
                  LocalTime startTime,
                  LocalTime endTime,
                  String tariffType,
                  Boolean active) {

        this.tariffName = tariffName;
        this.baseRate = baseRate;
        this.multiplier = multiplier;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tariffType = tariffType;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public Double getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(Double baseRate) {
        this.baseRate = baseRate;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getTariffType() {
        return tariffType;
    }

    public void setTariffType(String tariffType) {
        this.tariffType = tariffType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}