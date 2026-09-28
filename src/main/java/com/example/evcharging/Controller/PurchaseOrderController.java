package com.example.evcharging.Controller;

import com.example.evcharging.Dto.PurchaseOrderRequest;
import com.example.evcharging.Entity.PurchaseOrder;
import com.example.evcharging.Entity.Vendor;
import com.example.evcharging.Service.PurchaseOrderService;
import com.example.evcharging.Service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;
    private final VendorService vendorService;

    public PurchaseOrderController(
            PurchaseOrderService purchaseOrderService,
            VendorService vendorService) {

        this.purchaseOrderService = purchaseOrderService;
        this.vendorService = vendorService;
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(
            @RequestBody PurchaseOrderRequest request) {

        Vendor vendor = vendorService.getById(request.getVendorId())
                .orElseThrow(() ->
                        new RuntimeException("Vendor not found"));

        PurchaseOrder order = new PurchaseOrder();

        order.setOrderNumber(request.getOrderNumber());

        // PurchaseOrder uses LocalDateTime
        order.setOrderDate(LocalDateTime.now());

        order.setStatus("DRAFT");
        order.setTotalAmount(request.getTotalAmount());
        order.setVendor(vendor);

        return ResponseEntity.ok(
                purchaseOrderService.save(order)
        );
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {

        return ResponseEntity.ok(
                purchaseOrderService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(
            @PathVariable Long id) {

        return purchaseOrderService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<PurchaseOrder> confirmPurchaseOrder(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                purchaseOrderService.confirmOrder(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseOrder(
            @PathVariable Long id) {

        purchaseOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}