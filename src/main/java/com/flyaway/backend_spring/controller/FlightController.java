package com.flyaway.backend_spring.controller;

import com.flyaway.backend_spring.entity.Flight;
import com.flyaway.backend_spring.dto.FlightSearchRequest;
import com.flyaway.backend_spring.dto.FlightSearchResponse;
import com.flyaway.backend_spring.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    // Эндпоинт для получения всех рейсов (для отладки)
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Эндпоинт для поиска рейсов по airportCode (отправление и прибытие)
    @PostMapping("/search")
    public FlightSearchResponse searchFlights(@RequestBody FlightSearchRequest request) {
        List<Flight> departures = flightRepository.findByDepartureAirport(request.getAirportCode());
        List<Flight> arrivals = flightRepository.findByArrivalAirport(request.getAirportCode());
        return new FlightSearchResponse(departures, arrivals);
    }

    // Новый эндпоинт для получения деталей рейса по его ID
    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Integer flightId) {
        Optional<Flight> flightOpt = flightRepository.findById(flightId);
        if (flightOpt.isPresent()) {
            return ResponseEntity.ok(flightOpt.get());
        }
        return ResponseEntity.notFound().build();
    }
}
