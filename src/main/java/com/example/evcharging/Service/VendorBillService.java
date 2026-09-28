package com.example.evcharging.Service;

import com.example.evcharging.Entity.PurchaseOrder;
import com.example.evcharging.Entity.VendorBill;
import com.example.evcharging.Repository.PurchaseOrderRepository;
import com.example.evcharging.Repository.VendorBillRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VendorBillService {

    private final VendorBillRepository vendorBillRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public VendorBillService(VendorBillRepository vendorBillRepository,
                             PurchaseOrderRepository purchaseOrderRepository) {
        this.vendorBillRepository = vendorBillRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public VendorBill save(VendorBill bill) {
        return vendorBillRepository.save(bill);
    }

    public List<VendorBill> getAll() {
        return vendorBillRepository.findAll();
    }

    public Optional<VendorBill> getById(Long id) {
        return vendorBillRepository.findById(id);
    }

    public VendorBill createBillFromPurchaseOrder(Long purchaseOrderId) {

        PurchaseOrder order = purchaseOrderRepository.findById(purchaseOrderId)
                .orElseThrow(() ->
                        new RuntimeException("Purchase order not found"));

        VendorBill bill = new VendorBill();

        bill.setBillNumber("BILL-" + System.currentTimeMillis());
        bill.setBillDate(LocalDateTime.now());
        bill.setTotalAmount(order.getTotalAmount());
        bill.setPaidAmount(0.0);
        bill.setStatus("UNPAID");
        bill.setVendor(order.getVendor());
        bill.setPurchaseOrder(order);

        return vendorBillRepository.save(bill);
    }

    public VendorBill makePayment(Long billId, Double amount) {

        VendorBill bill = vendorBillRepository.findById(billId)
                .orElseThrow(() ->
                        new RuntimeException("Vendor bill not found"));

        if (amount == null || amount <= 0) {
            throw new RuntimeException("Payment amount must be greater than zero");
        }

        double currentPaid = bill.getPaidAmount() != null
                ? bill.getPaidAmount()
                : 0.0;

        double total = bill.getTotalAmount() != null
                ? bill.getTotalAmount()
                : 0.0;

        if (currentPaid + amount > total) {
            throw new RuntimeException(
                    "Payment cannot exceed vendor bill amount");
        }

        double newPaidAmount = currentPaid + amount;

        bill.setPaidAmount(newPaidAmount);

        if (newPaidAmount >= total) {
            bill.setStatus("PAID");
        } else {
            bill.setStatus("PARTIALLY_PAID");
        }

        return vendorBillRepository.save(bill);
    }

    public void delete(Long id) {
        vendorBillRepository.deleteById(id);
    }
}