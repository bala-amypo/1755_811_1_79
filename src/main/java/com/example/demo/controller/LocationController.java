package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(
            @RequestBody Location location) {

        Location savedLocation = locationService.createLocation(location);
        return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }
}
