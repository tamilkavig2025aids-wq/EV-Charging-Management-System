package com.example.evcharging.Repository;

import com.example.evcharging.Entity.VendorBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorBillRepository extends JpaRepository<VendorBill, Long> {
}