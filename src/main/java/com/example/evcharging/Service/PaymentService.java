package com.example.evcharging.Service;

import com.example.evcharging.Entity.Invoice;
import com.example.evcharging.Entity.Payment;
import com.example.evcharging.Repository.InvoiceRepository;
import com.example.evcharging.Repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          InvoiceRepository invoiceRepository) {
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment makePayment(Long invoiceId,
                               Double amount,
                               String paymentMethod) {

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() ->
                        new RuntimeException("Invoice not found"));

        if (amount == null || amount <= 0) {
            throw new RuntimeException("Payment amount must be greater than zero");
        }

        if (amount > invoice.getTotalAmount()) {
            throw new RuntimeException(
                    "Payment amount cannot exceed invoice amount");
        }

        Payment payment = new Payment();

        payment.setPaymentReference(
                "PAY-" + System.currentTimeMillis());

        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus("SUCCESS");
        payment.setInvoice(invoice);

        Payment savedPayment = paymentRepository.save(payment);

        if (amount >= invoice.getTotalAmount()) {
            invoice.setPaymentStatus("PAID");
        } else {
            invoice.setPaymentStatus("PARTIALLY_PAID");
        }

        invoiceRepository.save(invoice);

        return savedPayment;
    }

    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}