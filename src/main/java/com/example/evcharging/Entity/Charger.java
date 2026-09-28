package com.example.evcharging.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chargers")
public class Charger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chargerCode;

    private String chargerType; // DC_FAST, AC

    private Double powerCapacity;

    private String connectorType;

    private String status; // AVAILABLE, IN_USE, FAULTY, MAINTENANCE

    @ManyToOne
    @JoinColumn(name = "station_id")
    private ChargingStation station;

    public Charger() {
    }

    public Charger(String chargerCode,
                   String chargerType,
                   Double powerCapacity,
                   String connectorType,
                   String status,
                   ChargingStation station) {
        this.chargerCode = chargerCode;
        this.chargerType = chargerType;
        this.powerCapacity = powerCapacity;
        this.connectorType = connectorType;
        this.status = status;
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChargerCode() {
        return chargerCode;
    }

    public void setChargerCode(String chargerCode) {
        this.chargerCode = chargerCode;
    }

    public String getChargerType() {
        return chargerType;
    }

    public void setChargerType(String chargerType) {
        this.chargerType = chargerType;
    }

    public Double getPowerCapacity() {
        return powerCapacity;
    }

    public void setPowerCapacity(Double powerCapacity) {
        this.powerCapacity = powerCapacity;
    }

    public String getConnectorType() {
        return connectorType;
    }

    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ChargingStation getStation() {
        return station;
    }

    public void setStation(ChargingStation station) {
        this.station = station;
    }
}