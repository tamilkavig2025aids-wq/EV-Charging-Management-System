package com.example.evcharging.Service;

import com.example.evcharging.Entity.ChargingSession;
import com.example.evcharging.Entity.Tariff;
import com.example.evcharging.Repository.ChargingSessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChargingSessionService {

    private final ChargingSessionRepository chargingSessionRepository;

    public ChargingSessionService(
            ChargingSessionRepository chargingSessionRepository) {

        this.chargingSessionRepository = chargingSessionRepository;
    }

    // Save charging session
    public ChargingSession save(ChargingSession session) {
        return chargingSessionRepository.save(session);
    }

    // Get all charging sessions
    public List<ChargingSession> getAll() {
        return chargingSessionRepository.findAll();
    }

    // Get session by ID
    public Optional<ChargingSession> getById(Long id) {
        return chargingSessionRepository.findById(id);
    }

    // Start charging session
    public ChargingSession startSession(ChargingSession session) {

        if (session.getStartMeterReading() == null) {
            throw new RuntimeException(
                    "Start meter reading is required");
        }

        session.setStartTime(LocalDateTime.now());

        session.setStatus("STARTED");

        session.setTariffMultiplier(1.0);

        return chargingSessionRepository.save(session);
    }

    // Stop charging session
    public ChargingSession stopSession(
            Long id,
            Double endMeterReading,
            Tariff tariff) {

        ChargingSession session =
                chargingSessionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Charging session not found"));

        // Check session status
        if (!"STARTED".equals(session.getStatus())) {
            throw new RuntimeException(
                    "Charging session is not active");
        }

        // Validate end meter reading
        if (endMeterReading == null) {
            throw new RuntimeException(
                    "End meter reading is required");
        }

        if (session.getStartMeterReading() == null) {
            throw new RuntimeException(
                    "Start meter reading is missing");
        }

        if (endMeterReading <
                session.getStartMeterReading()) {

            throw new RuntimeException(
                    "End meter reading cannot be less than start meter reading");
        }

        // Set end time and meter reading
        session.setEndTime(LocalDateTime.now());

        session.setEndMeterReading(endMeterReading);

        // Calculate energy consumed
        double energyConsumed =
                endMeterReading -
                        session.getStartMeterReading();

        session.setEnergyConsumed(energyConsumed);

        // Default tariff values
        double baseRate = 8.0;
        double multiplier = 1.0;

        // Apply tariff
        if (tariff != null) {

            if (tariff.getBaseRate() != null) {
                baseRate = tariff.getBaseRate();
            }

            if (tariff.getMultiplier() != null) {
                multiplier = tariff.getMultiplier();
            }

            LocalTime currentTime =
                    session.getEndTime().toLocalTime();

            // Check tariff time period
            if (tariff.getStartTime() != null &&
                    tariff.getEndTime() != null) {

                boolean insideTariffPeriod =
                        isTimeWithinRange(
                                currentTime,
                                tariff.getStartTime(),
                                tariff.getEndTime());

                if (!insideTariffPeriod) {
                    multiplier = 1.0;
                }
            }
        }

        // Store tariff information
        session.setTariffRate(baseRate);

        session.setTariffMultiplier(multiplier);

        // Calculate total charging amount
        double totalAmount =
                energyConsumed *
                        baseRate *
                        multiplier;

        session.setTotalAmount(totalAmount);

        // Complete session
        session.setStatus("COMPLETED");

        return chargingSessionRepository.save(session);
    }

    // Check whether current time is inside tariff period
    private boolean isTimeWithinRange(
            LocalTime currentTime,
            LocalTime startTime,
            LocalTime endTime) {

        // Normal time range
        if (startTime.isBefore(endTime)) {

            return !currentTime.isBefore(startTime)
                    && !currentTime.isAfter(endTime);
        }

        // Overnight time range
        return !currentTime.isBefore(startTime)
                || !currentTime.isAfter(endTime);
    }

    // Delete session
    public void delete(Long id) {
        chargingSessionRepository.deleteById(id);
    }
}