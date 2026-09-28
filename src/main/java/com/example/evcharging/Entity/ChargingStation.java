package com.example.evcharging.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "charging_stations")
public class ChargingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stationName;

    private String location;

    private String city;

    private String status; // ACTIVE, INACTIVE, MAINTENANCE

    private Double totalPowerCapacity;

    public ChargingStation() {
    }

    public ChargingStation(String stationName,
                           String location,
                           String city,
                           String status,
                           Double totalPowerCapacity) {
        this.stationName = stationName;
        this.location = location;
        this.city = city;
        this.status = status;
        this.totalPowerCapacity = totalPowerCapacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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