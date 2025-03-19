package com.flyaway.backend_spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_flights", schema = "bookings")
@IdClass(TicketFlightId.class)
public class TicketFlight {

    @Id
    @Column(name = "ticket_no", length = 13)
    private String ticketNo;

    @Id
    @Column(name = "flight_id", nullable = false)
    private Integer flightId;

    @Column(name = "fare_conditions", nullable = false, length = 10)
    private String fareConditions;

    @Column(name = "amount", nullable = false)
    private Double amount;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFareConditions() {
        return fareConditions;
    }

    public void setFareConditions(String fareConditions) {
        this.fareConditions = fareConditions;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
