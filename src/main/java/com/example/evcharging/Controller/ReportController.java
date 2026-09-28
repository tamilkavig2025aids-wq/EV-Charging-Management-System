package com.example.evcharging.Controller;

import com.example.evcharging.Service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {

        return ResponseEntity.ok(
                reportService.getDashboardSummary()
        );
    }

    @GetMapping("/stations/{stationId}/yield")
    public ResponseEntity<Map<String, Object>> getStationYield(
            @PathVariable Long stationId) {

        return ResponseEntity.ok(
                reportService.getStationYield(stationId)
        );
    }
}