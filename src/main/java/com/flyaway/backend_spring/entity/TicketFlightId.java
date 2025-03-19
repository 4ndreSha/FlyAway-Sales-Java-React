package com.flyaway.backend_spring.entity;

import java.io.Serializable;
import java.util.Objects;

public class TicketFlightId implements Serializable {
    private String ticketNo;
    private Integer flightId;

    public TicketFlightId() {}

    public TicketFlightId(String ticketNo, Integer flightId) {
        this.ticketNo = ticketNo;
        this.flightId = flightId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketFlightId)) return false;
        TicketFlightId that = (TicketFlightId) o;
        return Objects.equals(ticketNo, that.ticketNo) &&
                Objects.equals(flightId, that.flightId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNo, flightId);
    }
}
