package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Location;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LocationRepository;
import com.example.demo.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location createLocation(Location location) {

        if (location.getLatitude() < -90 || location.getLatitude() > 90) {
            throw new IllegalArgumentException("Invalid latitude value");
        }

        if (location.getLongitude() < -180 || location.getLongitude() > 180) {
            throw new IllegalArgumentException("Invalid longitude value");
        }

        return locationRepository.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Location not found"));
    }
}
