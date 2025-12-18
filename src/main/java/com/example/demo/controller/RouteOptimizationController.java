package com.example.demo.controller;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/optimize")
public class RouteOptimizationController {

    private final RouteOptimizationService routeOptimizationService;

    public RouteOptimizationController(RouteOptimizationService routeOptimizationService) {
        this.routeOptimizationService = routeOptimizationService;
    }

    // Optimize route
    @PostMapping("/{shipmentId}")
    public RouteOptimizationResult optimizeRoute(@PathVariable Long shipmentId) {
        return routeOptimizationService.optimizeRoute(shipmentId);
    }

    // Get optimization result
    @GetMapping("/result/{resultId}")
    public RouteOptimizationResult getResult(@PathVariable Long resultId) {
        return routeOptimizationService.getResult(resultId);
    }
}
