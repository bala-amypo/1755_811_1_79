package com.example.demo.service.impl;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.entity.Shipment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RouteOptimizationRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RouteOptimizationServiceImpl implements RouteOptimizationService {
    private final RouteOptimizationRepository routeRepo;
    private final ShipmentRepository shipmentRepo;

    public RouteOptimizationServiceImpl(RouteOptimizationRepository routeRepo, ShipmentRepository shipmentRepo) {
        this.routeRepo = routeRepo;
        this.shipmentRepo = shipmentRepo;
    }

    @Override
    public RouteOptimizationResult optimizeRoute(Long shipmentId) {
        Shipment shipment = shipmentRepo.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));

        RouteOptimizationResult result = new RouteOptimizationResult();
        result.setShipment(shipment);
        
        // Dummy Distance Calculation (Euclidean)
        double latDiff = shipment.getDropLocation().getLatitude() - shipment.getPickupLocation().getLatitude();
        double lonDiff = shipment.getDropLocation().getLongitude() - shipment.getPickupLocation().getLongitude();
        double distance = Math.sqrt(Math.pow(latDiff, 2) + Math.pow(lonDiff, 2)) * 111.0; // approx km
        
        result.setOptimizedDistanceKm(distance > 0 ? distance : 10.0);
        result.setEstimatedFuelUsageL(result.getOptimizedDistanceKm() / shipment.getVehicle().getFuelEfficiency());
        result.setGeneratedAt(LocalDateTime.now());

        return routeRepo.save(result);
    }

    @Override
    public RouteOptimizationResult getResult(Long resultId) {
        return routeRepo.findById(resultId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}