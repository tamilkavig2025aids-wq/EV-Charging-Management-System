package com.example.evcharging.Service;

import com.example.evcharging.Entity.Tariff;
import com.example.evcharging.Repository.TariffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TariffService {

    private final TariffRepository tariffRepository;

    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public Tariff save(Tariff tariff) {
        return tariffRepository.save(tariff);
    }

    public List<Tariff> getAll() {
        return tariffRepository.findAll();
    }

    public Optional<Tariff> getById(Long id) {
        return tariffRepository.findById(id);
    }

    public void delete(Long id) {
        tariffRepository.deleteById(id);
    }
}