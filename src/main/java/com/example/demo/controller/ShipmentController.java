package com.example.demo.controller;

import com.example.demo.entity.Shipment;
import com.example.demo.service.ShipmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    // POST
    @PostMapping("/{vehicleId}")
    public Shipment create(@PathVariable Long vehicleId,
                           @RequestBody Shipment shipment) {
        return shipmentService.createShipment(vehicleId, shipment);
    }

    // GET
    @GetMapping("/{shipmentId}")
    public Shipment get(@PathVariable Long shipmentId) {
        return shipmentService.getShipment(shipmentId);
    }

    // DELETE
    @DeleteMapping("/{shipmentId}")
    public String delete(@PathVariable Long shipmentId) {
        shipmentService.deleteShipment(shipmentId);
        return "Shipment deleted successfully";
    }
}
