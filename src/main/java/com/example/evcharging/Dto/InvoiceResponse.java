package com.example.evcharging.Dto;

public class InvoiceResponse {

    private Long invoiceId;
    private String invoiceNumber;
    private String customerName;
    private Double energyConsumed;
    private Double ratePerKwh;
    private Double tariffMultiplier;
    private Double subtotal;
    private Double taxAmount;
    private Double totalAmount;
    private String paymentStatus;

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
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
}