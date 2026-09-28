package com.example.evcharging.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vendor_bill_lines")
public class VendorBillLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private String description;

    private Double quantity;

    private Double unitPrice;

    private Double lineTotal;

    @ManyToOne
    @JoinColumn(name = "vendor_bill_id")
    private VendorBill vendorBill;

    public VendorBillLine() {
    }

    public VendorBillLine(String itemName,
                          String description,
                          Double quantity,
                          Double unitPrice,
                          Double lineTotal,
                          VendorBill vendorBill) {

        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
        this.vendorBill = vendorBill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(Double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public VendorBill getVendorBill() {
        return vendorBill;
    }

    public void setVendorBill(VendorBill vendorBill) {
        this.vendorBill = vendorBill;
    }
}