package com.flyaway.backend_spring.controller;

import com.flyaway.backend_spring.entity.Airport;
import com.flyaway.backend_spring.repository.AirportRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportRepository airportRepository;

    public AirportController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
}