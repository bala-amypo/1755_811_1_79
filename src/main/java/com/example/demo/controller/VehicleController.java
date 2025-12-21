package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Vehicle> addVehicle(
            @PathVariable Long userId,
            @RequestBody Vehicle vehicle) {

        Vehicle savedVehicle = vehicleService.addVehicle(userId, vehicle);
        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                vehicleService.getVehiclesByUser(userId)
        );
    }
}
