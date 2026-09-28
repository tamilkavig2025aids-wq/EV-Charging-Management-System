package com.example.evcharging.Controller;

import com.example.evcharging.Dto.StationRequest;
import com.example.evcharging.Entity.ChargingStation;
import com.example.evcharging.Service.ChargingStationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ev/stations")
public class StationController {

    private final ChargingStationService stationService;

    public StationController(
            ChargingStationService stationService) {

        this.stationService = stationService;
    }

    @PostMapping
    public ResponseEntity<ChargingStation> create(
            @RequestBody StationRequest request) {

        ChargingStation station =
                new ChargingStation();

        station.setStationName(request.getStationName());
        station.setLocation(request.getLocation());
        station.setCity(request.getCity());
        station.setStatus(request.getStatus());
        station.setTotalPowerCapacity(
                request.getTotalPowerCapacity());

        return ResponseEntity.ok(
                stationService.save(station));
    }

    @GetMapping
    public ResponseEntity<List<ChargingStation>> getAll() {
        return ResponseEntity.ok(
                stationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChargingStation> getById(
            @PathVariable Long id) {

        return stationService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        stationService.delete(id);

        return ResponseEntity.ok(
                "Station deleted successfully");
    }
}