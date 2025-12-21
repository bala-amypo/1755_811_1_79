package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String vehicleNumber;
    private Double capacityKg;
    private Double fuelEfficiency;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public String getVehicleNumber() { return vehicleNumber; }

    public void setCapacityKg(Double capacityKg) { this.capacityKg = capacityKg; }
    public Double getCapacityKg() { return capacityKg; }

    public void setFuelEfficiency(Double fuelEfficiency) { this.fuelEfficiency = fuelEfficiency; }
    public Double getFuelEfficiency() { return fuelEfficiency; }

    public void setUser(User user) { this.user = user; }
    public User getUser() { return user; }
}