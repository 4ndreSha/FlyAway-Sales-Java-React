package com.flyaway.backend_spring.dto;

import com.flyaway.backend_spring.entity.Flight;
import java.util.List;

public class FlightSearchResponse {
    private List<Flight> departures;
    private List<Flight> arrivals;

    public FlightSearchResponse() {
    }

    public FlightSearchResponse(List<Flight> departures, List<Flight> arrivals) {
        this.departures = departures;
        this.arrivals = arrivals;
    }

    public List<Flight> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Flight> departures) {
        this.departures = departures;
    }

    public List<Flight> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<Flight> arrivals) {
        this.arrivals = arrivals;
    }
}
