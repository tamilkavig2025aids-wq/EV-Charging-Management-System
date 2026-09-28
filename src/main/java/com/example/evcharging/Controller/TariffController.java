package com.example.evcharging.Controller;

import com.example.evcharging.Dto.TariffRequest;
import com.example.evcharging.Entity.Tariff;
import com.example.evcharging.Service.TariffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ev/tariffs")
public class TariffController {

    private final TariffService tariffService;

    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    // Create tariff
    @PostMapping
    public ResponseEntity<Tariff> create(
            @RequestBody TariffRequest request) {

        Tariff tariff = new Tariff();

        tariff.setTariffName(request.getTariffName());
        tariff.setBaseRate(request.getBaseRate());
        tariff.setMultiplier(request.getMultiplier());
        tariff.setStartTime(request.getStartTime());
        tariff.setEndTime(request.getEndTime());
        tariff.setTariffType(request.getTariffType());
        tariff.setActive(request.getActive());

        return ResponseEntity.ok(
                tariffService.save(tariff));
    }

    // Get all tariffs
    @GetMapping
    public ResponseEntity<List<Tariff>> getAll() {

        return ResponseEntity.ok(
                tariffService.getAll());
    }

    // Get tariff by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tariff> getById(
            @PathVariable Long id) {

        return tariffService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete tariff
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        tariffService.delete(id);

        return ResponseEntity.ok(
                "Tariff deleted successfully");
    }
}