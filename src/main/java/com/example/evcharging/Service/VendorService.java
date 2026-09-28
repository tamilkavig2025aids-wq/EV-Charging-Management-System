package com.example.evcharging.Service;

import com.example.evcharging.Entity.Vendor;
import com.example.evcharging.Repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    public Optional<Vendor> getById(Long id) {
        return vendorRepository.findById(id);
    }

    public void delete(Long id) {
        vendorRepository.deleteById(id);
    }
}