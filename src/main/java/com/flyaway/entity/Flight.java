package com.flyaway.entity;

import java.sql.Timestamp;
import java.math.BigDecimal;

public class Flight {
    private int flightId;
    private String flightNo;
    private Timestamp scheduledDeparture;
    private Timestamp scheduledDepartureLocal;
    private Timestamp scheduledArrival;
    private Timestamp scheduledArrivalLocal;
    private String scheduledDuration; // Интервал можно сохранить как строку
    private String departureAirport;
    private String departureAirportName;
    private String departureCity;
    private String arrivalAirport;
    private String arrivalAirportName;
    private String arrivalCity;
    private String status;
    private String aircraftCode;
    private Timestamp actualDeparture;
    private Timestamp actualDepartureLocal;
    private Timestamp actualArrival;
    private Timestamp actualArrivalLocal;
    private String actualDuration;
    private BigDecimal price;
    private String airline;

    // Геттеры и сеттеры

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getAirline() {
        return airline;
    }
    public void setAirline(String airline) {
        this.airline = airline;
    }
    public int getFlightId() {
        return flightId;
    }
    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
    public String getFlightNo() {
        return flightNo;
    }
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }
    public Timestamp getScheduledDeparture() {
        return scheduledDeparture;
    }
    public void setScheduledDeparture(Timestamp scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }
    public Timestamp getScheduledDepartureLocal() {
        return scheduledDepartureLocal;
    }
    public void setScheduledDepartureLocal(Timestamp scheduledDepartureLocal) {
        this.scheduledDepartureLocal = scheduledDepartureLocal;
    }
    public Timestamp getScheduledArrival() {
        return scheduledArrival;
    }
    public void setScheduledArrival(Timestamp scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }
    public Timestamp getScheduledArrivalLocal() {
        return scheduledArrivalLocal;
    }
    public void setScheduledArrivalLocal(Timestamp scheduledArrivalLocal) {
        this.scheduledArrivalLocal = scheduledArrivalLocal;
    }
    public String getScheduledDuration() {
        return scheduledDuration;
    }
    public void setScheduledDuration(String scheduledDuration) {
        this.scheduledDuration = scheduledDuration;
    }
    public String getDepartureAirport() {
        return departureAirport;
    }
    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }
    public String getDepartureAirportName() {
        return departureAirportName;
    }
    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }
    public String getDepartureCity() {
        return departureCity;
    }
    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }
    public String getArrivalAirport() {
        return arrivalAirport;
    }
    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
    public String getArrivalAirportName() {
        return arrivalAirportName;
    }
    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }
    public String getArrivalCity() {
        return arrivalCity;
    }
    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAircraftCode() {
        return aircraftCode;
    }
    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }
    public Timestamp getActualDeparture() {
        return actualDeparture;
    }
    public void setActualDeparture(Timestamp actualDeparture) {
        this.actualDeparture = actualDeparture;
    }
    public Timestamp getActualDepartureLocal() {
        return actualDepartureLocal;
    }
    public void setActualDepartureLocal(Timestamp actualDepartureLocal) {
        this.actualDepartureLocal = actualDepartureLocal;
    }
    public Timestamp getActualArrival() {
        return actualArrival;
    }
    public void setActualArrival(Timestamp actualArrival) {
        this.actualArrival = actualArrival;
    }
    public Timestamp getActualArrivalLocal() {
        return actualArrivalLocal;
    }
    public void setActualArrivalLocal(Timestamp actualArrivalLocal) {
        this.actualArrivalLocal = actualArrivalLocal;
    }
    public String getActualDuration() {
        return actualDuration;
    }
    public void setActualDuration(String actualDuration) {
        this.actualDuration = actualDuration;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightNo='" + flightNo + '\'' +
                ", scheduledDeparture=" + scheduledDeparture +
                ", scheduledDepartureLocal=" + scheduledDepartureLocal +
                ", scheduledArrival=" + scheduledArrival +
                ", scheduledArrivalLocal=" + scheduledArrivalLocal +
                ", scheduledDuration='" + scheduledDuration + '\'' +
                ", departureAirport='" + departureAirport + '\'' +
                ", departureAirportName='" + departureAirportName + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", arrivalAirportName='" + arrivalAirportName + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", status='" + status + '\'' +
                ", aircraftCode='" + aircraftCode + '\'' +
                ", actualDeparture=" + actualDeparture +
                ", actualDepartureLocal=" + actualDepartureLocal +
                ", actualArrival=" + actualArrival +
                ", actualArrivalLocal=" + actualArrivalLocal +
                ", actualDuration='" + actualDuration + '\'' +
                '}';
    }
}
