package com.example.project.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "route_optimization_results")
public class RouteOptimizationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    private Double optimizedDistanceKm;
    private Double estimatedFuelUsageL;

    private LocalDateTime generatedAt;

    @PrePersist
    public void setGeneratedAt() {
        this.generatedAt = LocalDateTime.now();
    }

    // Constructors
    public RouteOptimizationResult() {}

    public RouteOptimizationResult(Long id, Shipment shipment,
                                   Double optimizedDistanceKm,
                                   Double estimatedFuelUsageL) {
        this.id = id;
        this.shipment = shipment;
        this.optimizedDistanceKm = optimizedDistanceKm;
        this.estimatedFuelUsageL = estimatedFuelUsageL;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Shipment getShipment() { return shipment; }
    public void setShipment(Shipment shipment) { this.shipment = shipment; }

    public Double getOptimizedDistanceKm() { return optimizedDistanceKm; }
    public void setOptimizedDistanceKm(Double optimizedDistanceKm) {
        this.optimizedDistanceKm = optimizedDistanceKm;
    }

    public Double getEstimatedFuelUsageL() { return estimatedFuelUsageL; }
    public void setEstimatedFuelUsageL(Double estimatedFuelUsageL) {
        this.estimatedFuelUsageL = estimatedFuelUsageL;
    }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
}
