package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ShipmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final VehicleRepository vehicleRepository;
    private final LocationRepository locationRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository,
                               VehicleRepository vehicleRepository,
                               LocationRepository locationRepository) {
        this.shipmentRepository = shipmentRepository;
        this.vehicleRepository = vehicleRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Shipment createShipment(Long vehicleId, Shipment shipment) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        Location pickup = locationRepository.findById(
                shipment.getPickupLocation().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Location not found"));

        Location drop = locationRepository.findById(
                shipment.getDropLocation().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Location not found"));

        if (shipment.getWeightKg() > vehicle.getCapacityKg()) {
            throw new IllegalArgumentException("exceeds");
        }

        if (shipment.getScheduledDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("past");
        }

        shipment.setVehicle(vehicle);
        shipment.setPickupLocation(pickup);
        shipment.setDropLocation(drop);

        return shipmentRepository.save(shipment);
    }

    @Override
    public Shipment getShipment(Long shipmentId) {
        return shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
    }

    @Override
    public void deleteShipment(Long shipmentId) {
        Shipment shipment = getShipment(shipmentId);
        shipmentRepository.delete(shipment);
    }
}
