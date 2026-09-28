package com.example.evcharging.Dto;

public class VendorBillRequest {

    private Long purchaseOrderId;

    public VendorBillRequest() {
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }
}