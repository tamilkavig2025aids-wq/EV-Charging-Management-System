package com.example.evcharging.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "charging_sessions")
public class ChargingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionCode;

    private String driverName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double startMeterReading;

    private Double endMeterReading;

    private Double energyConsumed;

    private Double tariffRate;

    private Double tariffMultiplier;

    private Double totalAmount;

    private String status; // STARTED, COMPLETED, CANCELLED

    @ManyToOne
    @JoinColumn(name = "charger_id")
    private Charger charger;

    public ChargingSession() {
    }

    public ChargingSession(String sessionCode,
                           String driverName,
                           LocalDateTime startTime,
                           LocalDateTime endTime,
                           Double startMeterReading,
                           Double endMeterReading,
                           Double energyConsumed,
                           Double tariffRate,
                           Double tariffMultiplier,
                           Double totalAmount,
                           String status,
                           Charger charger) {

        this.sessionCode = sessionCode;
        this.driverName = driverName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startMeterReading = startMeterReading;
        this.endMeterReading = endMeterReading;
        this.energyConsumed = energyConsumed;
        this.tariffRate = tariffRate;
        this.tariffMultiplier = tariffMultiplier;
        this.totalAmount = totalAmount;
        this.status = status;
        this.charger = charger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getStartMeterReading() {
        return startMeterReading;
    }

    public void setStartMeterReading(Double startMeterReading) {
        this.startMeterReading = startMeterReading;
    }

    public Double getEndMeterReading() {
        return endMeterReading;
    }

    public void setEndMeterReading(Double endMeterReading) {
        this.endMeterReading = endMeterReading;
    }

    public Double getEnergyConsumed() {
        return energyConsumed;
    }

    public void setEnergyConsumed(Double energyConsumed) {
        this.energyConsumed = energyConsumed;
    }

    public Double getTariffRate() {
        return tariffRate;
    }

    public void setTariffRate(Double tariffRate) {
        this.tariffRate = tariffRate;
    }

    public Double getTariffMultiplier() {
        return tariffMultiplier;
    }

    public void setTariffMultiplier(Double tariffMultiplier) {
        this.tariffMultiplier = tariffMultiplier;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Charger getCharger() {
        return charger;
    }

    public void setCharger(Charger charger) {
        this.charger = charger;
    }
}