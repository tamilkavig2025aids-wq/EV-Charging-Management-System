package com.example.evcharging.Service;

import com.example.evcharging.Entity.Charger;
import com.example.evcharging.Repository.ChargerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargerService {

    private final ChargerRepository chargerRepository;

    public ChargerService(ChargerRepository chargerRepository) {
        this.chargerRepository = chargerRepository;
    }

    public Charger save(Charger charger) {
        return chargerRepository.save(charger);
    }

    public List<Charger> getAll() {
        return chargerRepository.findAll();
    }

    public Optional<Charger> getById(Long id) {
        return chargerRepository.findById(id);
    }

    public void delete(Long id) {
        chargerRepository.deleteById(id);
    }
}