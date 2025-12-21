package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Shipment;
import com.example.demo.service.ShipmentService;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    @Autowired
    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/{vehicleId}")
    public ResponseEntity<Shipment> createShipment(
            @PathVariable Long vehicleId,
            @RequestBody Shipment shipment) {

        Shipment savedShipment =
                shipmentService.createShipment(vehicleId, shipment);

        return new ResponseEntity<>(savedShipment, HttpStatus.CREATED);
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<Shipment> getShipment(
            @PathVariable Long shipmentId) {

        return ResponseEntity.ok(
                shipmentService.getShipment(shipmentId)
        );
    }
}
