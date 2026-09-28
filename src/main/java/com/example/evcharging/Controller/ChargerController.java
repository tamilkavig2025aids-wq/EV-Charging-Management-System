package com.example.evcharging.Controller;

import com.example.evcharging.Dto.ChargerRequest;
import com.example.evcharging.Entity.Charger;
import com.example.evcharging.Entity.ChargingStation;
import com.example.evcharging.Service.ChargerService;
import com.example.evcharging.Service.ChargingStationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ev/chargers")
public class ChargerController {

    private final ChargerService chargerService;
    private final ChargingStationService stationService;

    public ChargerController(
            ChargerService chargerService,
            ChargingStationService stationService) {

        this.chargerService = chargerService;
        this.stationService = stationService;
    }

    @PostMapping
    public ResponseEntity<Charger> create(
            @RequestBody ChargerRequest request) {

        Charger charger = new Charger();

        charger.setChargerCode(request.getChargerCode());
        charger.setChargerType(request.getChargerType());
        charger.setPowerCapacity(request.getPowerCapacity());
        charger.setConnectorType(request.getConnectorType());
        charger.setStatus(request.getStatus());

        if (request.getStationId() != null) {

            ChargingStation station =
                    stationService.getById(request.getStationId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Charging station not found"));

            charger.setStation(station);
        }

        return ResponseEntity.ok(
                chargerService.save(charger));
    }

    @GetMapping
    public ResponseEntity<List<Charger>> getAll() {
        return ResponseEntity.ok(chargerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Charger> getById(
            @PathVariable Long id) {

        return chargerService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        chargerService.delete(id);

        return ResponseEntity.ok(
                "Charger deleted successfully");
    }
}