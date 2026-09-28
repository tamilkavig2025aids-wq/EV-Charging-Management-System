package com.example.evcharging.Dto;

public class StopSessionRequest {

    private Double endMeterReading;
    private Long tariffId;

    public Double getEndMeterReading() {
        return endMeterReading;
    }

    public void setEndMeterReading(Double endMeterReading) {
        this.endMeterReading = endMeterReading;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }
}