package com.example.evcharging.Dto;

public class StationRequest {

    private String stationName;
    private String location;
    private String city;
    private String status;
    private Double totalPowerCapacity;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPowerCapacity() {
        return totalPowerCapacity;
    }

    public void setTotalPowerCapacity(Double totalPowerCapacity) {
        this.totalPowerCapacity = totalPowerCapacity;
    }
}