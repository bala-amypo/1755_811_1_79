package com.example.demo.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "route_optimization_results")
public class RouteOptimizationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    @JsonIgnore
    private Shipment shipment;

    @Column(nullable = false)
    private Double optimizedDistanceKm;

    @Column(nullable = false)
    private Double estimatedFuelUsageL;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    public RouteOptimizationResult() {}

    public RouteOptimizationResult(Shipment shipment,
                                   Double optimizedDistanceKm,
                                   Double estimatedFuelUsageL,
                                   LocalDateTime generatedAt) {
        this.shipment = shipment;
        this.optimizedDistanceKm = optimizedDistanceKm;
        this.estimatedFuelUsageL = estimatedFuelUsageL;
        this.generatedAt = generatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.generatedAt = LocalDateTime.now();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Double getOptimizedDistanceKm() {
        return optimizedDistanceKm;
    }

    public Double getEstimatedFuelUsageL() {
        return estimatedFuelUsageL;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
}
