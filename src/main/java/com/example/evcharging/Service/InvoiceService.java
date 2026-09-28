package com.example.evcharging.Service;

import com.example.evcharging.Entity.ChargingSession;
import com.example.evcharging.Entity.Invoice;
import com.example.evcharging.Repository.ChargingSessionRepository;
import com.example.evcharging.Repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ChargingSessionRepository chargingSessionRepository;

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            ChargingSessionRepository chargingSessionRepository) {

        this.invoiceRepository = invoiceRepository;
        this.chargingSessionRepository = chargingSessionRepository;
    }

    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice createInvoiceForSession(Long sessionId) {

        ChargingSession session =
                chargingSessionRepository.findById(sessionId)
                        .orElseThrow(() ->
                                new RuntimeException("Charging session not found"));

        if (!"COMPLETED".equals(session.getStatus())) {
            throw new RuntimeException(
                    "Invoice can only be created for a completed charging session");
        }

        double energyConsumed =
                session.getEnergyConsumed() != null
                        ? session.getEnergyConsumed()
                        : 0.0;

        double rate =
                session.getTariffRate() != null
                        ? session.getTariffRate()
                        : 0.0;

        double multiplier =
                session.getTariffMultiplier() != null
                        ? session.getTariffMultiplier()
                        : 1.0;

        double subtotal =
                energyConsumed * rate * multiplier;

        double taxRate = 0.18;

        double taxAmount =
                subtotal * taxRate;

        double totalAmount =
                subtotal + taxAmount;

        Invoice invoice = new Invoice();

        invoice.setInvoiceNumber(
                "INV-" + System.currentTimeMillis());

        invoice.setCustomerName(
                session.getDriverName());

        invoice.setInvoiceDate(
                LocalDateTime.now());

        invoice.setEnergyConsumed(
                energyConsumed);

        invoice.setRatePerKwh(
                rate);

        invoice.setTariffMultiplier(
                multiplier);

        invoice.setSubtotal(
                subtotal);

        invoice.setTaxAmount(
                taxAmount);

        invoice.setTotalAmount(
                totalAmount);

        invoice.setPaymentStatus(
                "UNPAID");

        invoice.setChargingSession(
                session);

        return invoiceRepository.save(invoice);
    }

    public void delete(Long id) {
        invoiceRepository.deleteById(id);
    }
}