package com.flyaway.dao;

import com.flyaway.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.UUID;

public class PurchaseTicketDao {

    public boolean purchaseTicket(String flightId, int passengerId, BigDecimal amount, String fareConditions, String contactData) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String bookRef = generateBookRef();
            String ticketNo = generateTicketNo();
            Timestamp now = new Timestamp(System.currentTimeMillis());

            // 1. Создание бронирования
            String insertBooking = "INSERT INTO bookings(book_ref, book_date, total_amount) VALUES (?, ?, ?)";
            try (PreparedStatement psBooking = conn.prepareStatement(insertBooking)) {
                psBooking.setString(1, bookRef);
                psBooking.setTimestamp(2, now);
                psBooking.setBigDecimal(3, amount);
                psBooking.executeUpdate();
            }

            // 2. Создание билета с привязкой к пассажиру
            String insertTicket = "INSERT INTO tickets(ticket_no, book_ref, passenger_id, contact_data) " +
                    "VALUES (?, ?, ?, ?::jsonb)";
            try (PreparedStatement psTicket = conn.prepareStatement(insertTicket)) {
                psTicket.setString(1, ticketNo);
                psTicket.setString(2, bookRef);
                psTicket.setInt(3, passengerId);
                psTicket.setString(4, contactData);
                psTicket.executeUpdate();
            }

            // 3. Привязка билета к рейсу
            String insertTicketFlight = "INSERT INTO ticket_flights(ticket_no, flight_id, fare_conditions, amount) VALUES (?, ?, ?, ?)";
            try (PreparedStatement psTicketFlight = conn.prepareStatement(insertTicketFlight)) {
                psTicketFlight.setString(1, ticketNo);
                psTicketFlight.setInt(2, Integer.parseInt(flightId));
                psTicketFlight.setString(3, fareConditions);
                psTicketFlight.setBigDecimal(4, amount);
                psTicketFlight.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Rollback error: " + ex.getMessage());
                }
            }
            System.err.println("Ошибка при покупке билета: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) { /* логируем ошибку */ }
            }
        }
    }

    private String generateBookRef() {
        return "BR" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }

    private String generateTicketNo() {
        // Генерируем 6-символьный номер (пример: TN5X9Z)
        return "TN" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();
    }
}
