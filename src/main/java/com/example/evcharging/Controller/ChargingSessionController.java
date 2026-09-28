package com.example.evcharging.Controller;

import com.example.evcharging.Dto.ChargingSessionRequest;
import com.example.evcharging.Dto.StopSessionRequest;
import com.example.evcharging.Entity.Charger;
import com.example.evcharging.Entity.ChargingSession;
import com.example.evcharging.Entity.Tariff;
import com.example.evcharging.Service.ChargerService;
import com.example.evcharging.Service.ChargingSessionService;
import com.example.evcharging.Service.TariffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ev/sessions")
public class ChargingSessionController {

    private final ChargingSessionService sessionService;
    private final ChargerService chargerService;
    private final TariffService tariffService;

    public ChargingSessionController(
            ChargingSessionService sessionService,
            ChargerService chargerService,
            TariffService tariffService) {

        this.sessionService = sessionService;
        this.chargerService = chargerService;
        this.tariffService = tariffService;
    }

    // Start charging
    @PostMapping
    public ResponseEntity<ChargingSession> startSession(
            @RequestBody ChargingSessionRequest request) {

        ChargingSession session =
                new ChargingSession();

        session.setSessionCode(request.getSessionCode());
        session.setDriverName(request.getDriverName());
        session.setStartMeterReading(
                request.getStartMeterReading());

        if (request.getChargerId() != null) {

            Charger charger =
                    chargerService.getById(
                                    request.getChargerId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Charger not found"));

            session.setCharger(charger);
        }

        return ResponseEntity.ok(
                sessionService.startSession(session));
    }

    // Get all sessions
    @GetMapping
    public ResponseEntity<List<ChargingSession>> getAll() {
        return ResponseEntity.ok(
                sessionService.getAll());
    }

    // Get session by ID
    @GetMapping("/{id}")
    public ResponseEntity<ChargingSession> getById(
            @PathVariable Long id) {

        return sessionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Stop charging
    @PutMapping("/{id}/stop")
    public ResponseEntity<ChargingSession> stopSession(
            @PathVariable Long id,
            @RequestBody StopSessionRequest request) {

        Tariff tariff = null;

        if (request.getTariffId() != null) {

            tariff =
                    tariffService.getById(
                                    request.getTariffId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Tariff not found"));
        }

        ChargingSession completedSession =
                sessionService.stopSession(
                        id,
                        request.getEndMeterReading(),
                        tariff);

        return ResponseEntity.ok(completedSession);
    }

    // Delete session
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        sessionService.delete(id);

        return ResponseEntity.ok(
                "Charging session deleted successfully");
    }
}