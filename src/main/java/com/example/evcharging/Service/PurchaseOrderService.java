package com.example.evcharging.Service;

import com.example.evcharging.Entity.PurchaseOrder;
import com.example.evcharging.Repository.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public PurchaseOrder save(PurchaseOrder order) {
        return purchaseOrderRepository.save(order);
    }

    public List<PurchaseOrder> getAll() {
        return purchaseOrderRepository.findAll();
    }

    public Optional<PurchaseOrder> getById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    public PurchaseOrder confirmOrder(Long id) {

        PurchaseOrder order = purchaseOrderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Purchase order not found"));

        order.setStatus("CONFIRMED");

        return purchaseOrderRepository.save(order);
    }

    public void delete(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}