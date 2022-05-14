package com.krukovska.task4.controller;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.service.DriverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DriverController {

    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping("/driver/all")
    public List<Driver> getAllDrivers() {
        return service.findAll();
    }
}
