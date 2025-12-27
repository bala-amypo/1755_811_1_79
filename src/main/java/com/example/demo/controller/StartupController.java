package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartupController {

    @GetMapping("/")
    public String home() {
        return "Server started";
    }

    @GetMapping("/status")
    public String status() {
        return "OK";
    }
}
