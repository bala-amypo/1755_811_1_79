package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.RouteOptimizationResult;

@Repository
public interface RouteOptimizationResultRepository
        extends JpaRepository<RouteOptimizationResult, Long> {
}
