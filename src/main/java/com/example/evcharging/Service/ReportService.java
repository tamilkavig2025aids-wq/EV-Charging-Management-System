package com.example.evcharging.Service;

import com.example.evcharging.Entity.*;
import com.example.evcharging.Repository.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final ChargingStationRepository chargingStationRepository;
    private final ChargingSessionRepository chargingSessionRepository;
    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;
    private final VendorBillRepository vendorBillRepository;
    private final BudgetRepository budgetRepository;

    public ReportService(
            ChargingStationRepository chargingStationRepository,
            ChargingSessionRepository chargingSessionRepository,
            InvoiceRepository invoiceRepository,
            PaymentRepository paymentRepository,
            VendorBillRepository vendorBillRepository,
            BudgetRepository budgetRepository) {

        this.chargingStationRepository = chargingStationRepository;
        this.chargingSessionRepository = chargingSessionRepository;
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
        this.vendorBillRepository = vendorBillRepository;
        this.budgetRepository = budgetRepository;
    }

    public Map<String, Object> getDashboardSummary() {

        Map<String, Object> report = new HashMap<>();

        List<ChargingStation> stations =
                chargingStationRepository.findAll();

        List<ChargingSession> sessions =
                chargingSessionRepository.findAll();

        List<Invoice> invoices =
                invoiceRepository.findAll();

        List<Payment> payments =
                paymentRepository.findAll();

        List<VendorBill> bills =
                vendorBillRepository.findAll();

        List<Budget> budgets =
                budgetRepository.findAll();

        double totalEnergy = sessions.stream()
                .filter(s -> s.getEnergyConsumed() != null)
                .mapToDouble(ChargingSession::getEnergyConsumed)
                .sum();

        double totalRevenue = invoices.stream()
                .filter(i -> i.getTotalAmount() != null)
                .mapToDouble(Invoice::getTotalAmount)
                .sum();

        double totalPayments = payments.stream()
                .filter(p -> p.getAmount() != null)
                .mapToDouble(Payment::getAmount)
                .sum();

        double totalUtilityCost = bills.stream()
                .filter(b -> b.getTotalAmount() != null)
                .mapToDouble(VendorBill::getTotalAmount)
                .sum();

        double estimatedProfit = totalRevenue - totalUtilityCost;

        report.put("totalStations", stations.size());
        report.put("totalSessions", sessions.size());
        report.put("totalInvoices", invoices.size());
        report.put("totalPayments", payments.size());
        report.put("totalBudgets", budgets.size());

        report.put("totalEnergyConsumed", totalEnergy);
        report.put("totalRevenue", totalRevenue);
        report.put("totalPaymentsReceived", totalPayments);
        report.put("totalUtilityCost", totalUtilityCost);
        report.put("estimatedProfit", estimatedProfit);

        return report;
    }

    public Map<String, Object> getStationYield(Long stationId) {

        Map<String, Object> result = new HashMap<>();

        ChargingStation station =
                chargingStationRepository.findById(stationId)
                        .orElseThrow(() ->
                                new RuntimeException("Station not found"));

        List<ChargingSession> sessions =
                chargingSessionRepository.findAll();

        double energy = sessions.stream()
                .filter(s -> s.getCharger() != null)
                .filter(s -> s.getCharger().getStation() != null)
                .filter(s -> s.getCharger().getStation()
                        .getId().equals(stationId))
                .filter(s -> s.getEnergyConsumed() != null)
                .mapToDouble(ChargingSession::getEnergyConsumed)
                .sum();

        double revenue = sessions.stream()
                .filter(s -> s.getCharger() != null)
                .filter(s -> s.getCharger().getStation() != null)
                .filter(s -> s.getCharger().getStation()
                        .getId().equals(stationId))
                .filter(s -> s.getTotalAmount() != null)
                .mapToDouble(ChargingSession::getTotalAmount)
                .sum();

        result.put("stationId", station.getId());
        result.put("stationName", station.getStationName());
        result.put("location", station.getLocation());
        result.put("energyConsumed", energy);
        result.put("revenue", revenue);

        return result;
    }
}