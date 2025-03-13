package com.flyaway.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TicketDetails {
    // Данные из ticket_flights
    private String ticketNo;
    private int flightId;
    private String fareConditions;
    private BigDecimal amount;
    // Данные из bookings
    private String bookRef;
    private Timestamp bookDate;
    private BigDecimal totalAmount;
    // Данные из flights
    private String flightNo;
    private Timestamp scheduledDeparture;
    private Timestamp scheduledArrival;
    private String departureAirport;
    private String arrivalAirport;
    private String status;
    private String aircraftCode;
    private Timestamp actualDeparture;
    private Timestamp actualArrival;

    // Getters & Setters
    public String getTicketNo() {
        return ticketNo;
    }
    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }
    public int getFlightId() {
        return flightId;
    }
    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
    public String getFareConditions() {
        return fareConditions;
    }
    public void setFareConditions(String fareConditions) {
        this.fareConditions = fareConditions;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getBookRef() {
        return bookRef;
    }
    public void setBookRef(String bookRef) {
        this.bookRef = bookRef;
    }
    public Timestamp getBookDate() {
        return bookDate;
    }
    public void setBookDate(Timestamp bookDate) {
        this.bookDate = bookDate;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
    public Timestamp getScheduledArrival() {
        return scheduledArrival;
    }
    public void setScheduledArrival(Timestamp scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }
    public String getDepartureAirport() {
        return departureAirport;
    }
    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }
    public String getArrivalAirport() {
        return arrivalAirport;
    }
    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
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
    public Timestamp getActualArrival() {
        return actualArrival;
    }
    public void setActualArrival(Timestamp actualArrival) {
        this.actualArrival = actualArrival;
    }
}
