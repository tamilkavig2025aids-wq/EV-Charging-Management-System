package com.example.evcharging.Controller;

import com.example.evcharging.Entity.VendorBill;
import com.example.evcharging.Service.VendorBillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-bills")
public class VendorBillController {

    private final VendorBillService vendorBillService;

    public VendorBillController(VendorBillService vendorBillService) {
        this.vendorBillService = vendorBillService;
    }

    @PostMapping
    public ResponseEntity<VendorBill> createVendorBill(
            @RequestParam Long purchaseOrderId) {

        return ResponseEntity.ok(
                vendorBillService.createBillFromPurchaseOrder(
                        purchaseOrderId
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<VendorBill>> getAllVendorBills() {
        return ResponseEntity.ok(
                vendorBillService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorBill> getVendorBillById(
            @PathVariable Long id) {

        return vendorBillService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<VendorBill> payVendorBill(
            @PathVariable Long id,
            @RequestParam Double amount) {

        return ResponseEntity.ok(
                vendorBillService.makePayment(id, amount)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendorBill(
            @PathVariable Long id) {

        vendorBillService.delete(id);
        return ResponseEntity.noContent().build();
    }
}