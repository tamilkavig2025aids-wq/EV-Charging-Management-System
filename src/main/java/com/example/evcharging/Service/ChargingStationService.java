package com.example.evcharging.Service;

import com.example.evcharging.Entity.ChargingStation;
import com.example.evcharging.Repository.ChargingStationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;

    public ChargingStationService(ChargingStationRepository chargingStationRepository) {
        this.chargingStationRepository = chargingStationRepository;
    }

    public ChargingStation save(ChargingStation station) {
        return chargingStationRepository.save(station);
    }

    public List<ChargingStation> getAll() {
        return chargingStationRepository.findAll();
    }

    public Optional<ChargingStation> getById(Long id) {
        return chargingStationRepository.findById(id);
    }

    public void delete(Long id) {
        chargingStationRepository.deleteById(id);
    }
}