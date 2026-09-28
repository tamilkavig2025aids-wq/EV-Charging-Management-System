package com.example.evcharging.Dto;

public class ChargingSessionRequest {

    private String sessionCode;
    private String driverName;
    private Double startMeterReading;
    private Long chargerId;

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Double getStartMeterReading() {
        return startMeterReading;
    }

    public void setStartMeterReading(Double startMeterReading) {
        this.startMeterReading = startMeterReading;
    }

    public Long getChargerId() {
        return chargerId;
    }

    public void setChargerId(Long chargerId) {
        this.chargerId = chargerId;
    }
}