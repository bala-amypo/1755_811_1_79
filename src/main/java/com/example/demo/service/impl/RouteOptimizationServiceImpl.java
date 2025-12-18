package com.example.demo.service.impl;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.entity.Shipment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RouteOptimizationResultRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RouteOptimizationServiceImpl implements RouteOptimizationService {
    private final RouteOptimizationResultRepository routeRepo;
    private final ShipmentRepository shipmentRepo;

    public RouteOptimizationServiceImpl(RouteOptimizationResultRepository routeRepo, ShipmentRepository shipmentRepo) {
        this.routeRepo = routeRepo;
        this.shipmentRepo = shipmentRepo;
    }

    @Override
    public RouteOptimizationResult optimizeRoute(Long shipmentId) {
        Shipment shipment = shipmentRepo.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));

        RouteOptimizationResult result = new RouteOptimizationResult();
        result.setShipment(shipment);
        
        // Calculation of dummy distance and fuel
        double dummyDistance = 120.5; // Example km
        double fuelUsage = dummyDistance / shipment.getVehicle().getFuelEfficiency();
        
        result.setOptimizedDistanceKm(dummyDistance);
        result.setEstimatedFuelUsageL(fuelUsage);
        result.setGeneratedAt(LocalDateTime.now()); // Auto-set timestamp

        return routeRepo.save(result);
    }

    @Override
    public RouteOptimizationResult getResult(Long resultId) {
        return routeRepo.findById(resultId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}