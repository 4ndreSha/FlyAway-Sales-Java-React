package com.flyaway.backend_spring.repository;

import com.flyaway.backend_spring.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByDepartureAirport(String airport);
    List<Flight> findByArrivalAirport(String airport);
}
