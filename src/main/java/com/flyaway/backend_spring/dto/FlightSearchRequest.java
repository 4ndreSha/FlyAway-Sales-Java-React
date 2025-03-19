package com.flyaway.backend_spring.dto;

public class FlightSearchRequest {
    private String airportCode;

    public FlightSearchRequest() {
    }

    public FlightSearchRequest(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }
}
