package com.example.evcharging.Dto;

public class StationYieldResponse {

    private Long stationId;
    private String stationName;
    private String location;
    private Double energyConsumed;
    private Double revenue;

    public StationYieldResponse() {
    }

    public StationYieldResponse(
            Long stationId,
            String stationName,
            String location,
            Double energyConsumed,
            Double revenue) {

        this.stationId = stationId;
        this.stationName = stationName;
        this.location = location;
        this.energyConsumed = energyConsumed;
        this.revenue = revenue;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getEnergyConsumed() {
        return energyConsumed;
    }

    public void setEnergyConsumed(Double energyConsumed) {
        this.energyConsumed = energyConsumed;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
}