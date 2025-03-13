package com.flyaway.dao;

import com.flyaway.model.TicketDetails;
import com.flyaway.model.Ticket;
import com.flyaway.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {
    public List<Ticket> getTicketsByPassenger(int passengerId) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT t.ticket_no, t.book_ref, t.contact_data, t.passenger_id, " +
                "b.book_date, b.total_amount " +
                "FROM tickets t " +
                "JOIN bookings b ON t.book_ref = b.book_ref " +
                "WHERE t.passenger_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, passengerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setTicketNo(rs.getString("ticket_no"));
                    ticket.setBookRef(rs.getString("book_ref"));
                    ticket.setContactData(rs.getString("contact_data"));
                    ticket.setPassengerId(rs.getInt("passenger_id"));
                    ticket.setBookDate(rs.getTimestamp("book_date"));
                    ticket.setTotalAmount(rs.getBigDecimal("total_amount"));
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка получения билетов: " + e.getMessage());
        }
        return tickets;
    }

    // Новый метод для получения подробностей билета по ticketNo (если требуется)
    public Ticket getTicketByTicketNo(String ticketNo) {
        String sql = "SELECT t.ticket_no, t.book_ref, t.contact_data, t.passenger_id, " +
                "b.book_date, b.total_amount " +
                "FROM tickets t " +
                "JOIN bookings b ON t.book_ref = b.book_ref " +
                "WHERE t.ticket_no = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ticketNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setTicketNo(rs.getString("ticket_no"));
                    ticket.setBookRef(rs.getString("book_ref"));
                    ticket.setContactData(rs.getString("contact_data"));
                    ticket.setPassengerId(rs.getInt("passenger_id"));
                    ticket.setBookDate(rs.getTimestamp("book_date"));
                    ticket.setTotalAmount(rs.getBigDecimal("total_amount"));
                    return ticket;
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка получения деталей билета: " + e.getMessage());
        }
        return null;
    }

    public TicketDetails getTicketDetailsByTicketNo(String ticketNo) {
        String sql = "SELECT t.ticket_no, t.book_ref, t.contact_data, t.passenger_id, " +
                "b.book_date, b.total_amount, " +
                "tf.flight_id, tf.fare_conditions, tf.amount, " +
                "f.flight_no, f.scheduled_departure, f.scheduled_arrival, " +
                "f.departure_airport, f.arrival_airport, f.status, f.aircraft_code, " +
                "f.actual_departure, f.actual_arrival " +
                "FROM tickets t " +
                "JOIN ticket_flights tf ON t.ticket_no = tf.ticket_no " +
                "JOIN bookings b ON t.book_ref = b.book_ref " +
                "JOIN flights f ON tf.flight_id = f.flight_id " +
                "WHERE t.ticket_no = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ticketNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TicketDetails td = new TicketDetails();
                    td.setTicketNo(rs.getString("ticket_no"));
                    td.setFlightId(rs.getInt("flight_id"));
                    td.setFareConditions(rs.getString("fare_conditions"));
                    td.setAmount(rs.getBigDecimal("amount"));
                    td.setBookRef(rs.getString("book_ref"));
                    td.setBookDate(rs.getTimestamp("book_date"));
                    td.setTotalAmount(rs.getBigDecimal("total_amount"));
                    td.setFlightNo(rs.getString("flight_no"));
                    td.setScheduledDeparture(rs.getTimestamp("scheduled_departure"));
                    td.setScheduledArrival(rs.getTimestamp("scheduled_arrival"));
                    td.setDepartureAirport(rs.getString("departure_airport"));
                    td.setArrivalAirport(rs.getString("arrival_airport"));
                    td.setStatus(rs.getString("status"));
                    td.setAircraftCode(rs.getString("aircraft_code"));
                    td.setActualDeparture(rs.getTimestamp("actual_departure"));
                    td.setActualArrival(rs.getTimestamp("actual_arrival"));
                    return td;
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка получения деталей билета: " + e.getMessage());
        }
        return null;
    }
}
