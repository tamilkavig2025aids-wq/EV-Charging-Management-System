package com.example.evcharging.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;

    private String customerName;

    private LocalDateTime invoiceDate;

    private Double energyConsumed;

    private Double ratePerKwh;

    private Double tariffMultiplier;

    private Double subtotal;

    private Double taxAmount;

    private Double totalAmount;

    private String paymentStatus; // UNPAID, PAID, PARTIALLY_PAID

    @OneToOne
    @JoinColumn(name = "charging_session_id")
    private ChargingSession chargingSession;

    public Invoice() {
    }

    public Invoice(String invoiceNumber,
                   String customerName,
                   LocalDateTime invoiceDate,
                   Double energyConsumed,
                   Double ratePerKwh,
                   Double tariffMultiplier,
                   Double subtotal,
                   Double taxAmount,
                   Double totalAmount,
                   String paymentStatus,
                   ChargingSession chargingSession) {

        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
        this.energyConsumed = energyConsumed;
        this.ratePerKwh = ratePerKwh;
        this.tariffMultiplier = tariffMultiplier;
        this.subtotal = subtotal;
        this.taxAmount = taxAmount;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.chargingSession = chargingSession;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getEnergyConsumed() {
        return energyConsumed;
    }

    public void setEnergyConsumed(Double energyConsumed) {
        this.energyConsumed = energyConsumed;
    }

    public Double getRatePerKwh() {
        return ratePerKwh;
    }

    public void setRatePerKwh(Double ratePerKwh) {
        this.ratePerKwh = ratePerKwh;
    }

    public Double getTariffMultiplier() {
        return tariffMultiplier;
    }

    public void setTariffMultiplier(Double tariffMultiplier) {
        this.tariffMultiplier = tariffMultiplier;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public ChargingSession getChargingSession() {
        return chargingSession;
    }

    public void setChargingSession(ChargingSession chargingSession) {
        this.chargingSession = chargingSession;
    }
}