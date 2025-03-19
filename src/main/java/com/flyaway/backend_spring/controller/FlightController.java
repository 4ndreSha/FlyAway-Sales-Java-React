package com.flyaway.backend_spring.controller;

import com.flyaway.backend_spring.entity.Flight;
import com.flyaway.backend_spring.dto.FlightSearchRequest;
import com.flyaway.backend_spring.dto.FlightSearchResponse;
import com.flyaway.backend_spring.repository.FlightRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @PostMapping("/search")
    public FlightSearchResponse searchFlights(@RequestBody FlightSearchRequest request) {
        List<Flight> departures = flightRepository.findByDepartureAirport(request.getAirportCode());
        List<Flight> arrivals = flightRepository.findByArrivalAirport(request.getAirportCode());
        return new FlightSearchResponse(departures, arrivals);
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Integer flightId) {
        Optional<Flight> flightOpt = flightRepository.findById(flightId);
        return flightOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}