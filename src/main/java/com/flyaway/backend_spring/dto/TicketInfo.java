package com.flyaway.backend_spring.dto;

import java.time.ZonedDateTime;

public class TicketInfo {
    private String ticketNo;
    private String flightNo;
    private ZonedDateTime scheduledDeparture;
    private String status;

    public TicketInfo(String ticketNo, String flightNo, ZonedDateTime scheduledDeparture, String status) {
        this.ticketNo = ticketNo;
        this.flightNo = flightNo;
        this.scheduledDeparture = scheduledDeparture;
        this.status = status;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public ZonedDateTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(ZonedDateTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
