package com.example.evcharging.Controller;

import com.example.evcharging.Dto.InvoiceResponse;
import com.example.evcharging.Entity.Invoice;
import com.example.evcharging.Service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ev/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Create invoice for completed charging session
    @PostMapping("/session/{sessionId}")
    public ResponseEntity<InvoiceResponse> createInvoice(
            @PathVariable Long sessionId) {

        Invoice invoice =
                invoiceService.createInvoiceForSession(sessionId);

        InvoiceResponse response =
                convertToResponse(invoice);

        return ResponseEntity.ok(response);
    }

    // Get all invoices
    @GetMapping
    public ResponseEntity<List<Invoice>> getAll() {

        return ResponseEntity.ok(
                invoiceService.getAll());
    }

    // Get invoice by ID
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(
            @PathVariable Long id) {

        return invoiceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete invoice
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        invoiceService.delete(id);

        return ResponseEntity.ok(
                "Invoice deleted successfully");
    }

    // Convert Entity to DTO
    private InvoiceResponse convertToResponse(
            Invoice invoice) {

        InvoiceResponse response =
                new InvoiceResponse();

        response.setInvoiceId(invoice.getId());
        response.setInvoiceNumber(
                invoice.getInvoiceNumber());
        response.setCustomerName(
                invoice.getCustomerName());
        response.setEnergyConsumed(
                invoice.getEnergyConsumed());
        response.setRatePerKwh(
                invoice.getRatePerKwh());
        response.setTariffMultiplier(
                invoice.getTariffMultiplier());
        response.setSubtotal(
                invoice.getSubtotal());
        response.setTaxAmount(
                invoice.getTaxAmount());
        response.setTotalAmount(
                invoice.getTotalAmount());
        response.setPaymentStatus(
                invoice.getPaymentStatus());

        return response;
    }
}