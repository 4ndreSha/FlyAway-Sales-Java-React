package com.flyaway.jdbcapp.dao;

import com.flyaway.jdbcapp.entity.Booking;
import com.flyaway.jdbcapp.services.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {

    // Метод для создания новой записи (Create)
    public boolean createBooking(int bookRef, Timestamp bookDate, BigDecimal totalAmount) {
        String sql = "INSERT INTO bookings(book_ref, book_date, total_amount) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookRef);
            pstmt.setTimestamp(2, bookDate);
            pstmt.setBigDecimal(3, totalAmount);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при создании бронирования: " + e.getMessage());
        }
        return false;
    }

    // Метод для получения бронирования по book_ref (Read)
    public Booking getBooking(int bookRef) {
        String sql = "SELECT book_ref, book_date, total_amount FROM bookings WHERE book_ref = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookRef);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookRef(rs.getInt("book_ref"));
                    booking.setBookDate(rs.getTimestamp("book_date"));
                    booking.setTotalAmount(rs.getInt("total_amount"));
                    return booking;
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении бронирования: " + e.getMessage());
        }
        return null;
    }

    // Метод для обновления бронирования (Update)
    public boolean updateBooking(int bookRef, Timestamp newDate, int newTotalAmount) {
        String sql = "UPDATE bookings SET book_date = ?, total_amount = ? WHERE book_ref = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, newDate);
            pstmt.setInt(2, newTotalAmount);
            pstmt.setInt(3, bookRef);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении бронирования: " + e.getMessage());
        }
        return false;
    }

    // Метод для удаления бронирования (Delete)
    public boolean deleteBooking(int bookRef) {
        String sql = "DELETE FROM bookings WHERE book_ref = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookRef);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении бронирования: " + e.getMessage());
        }
        return false;
    }

    // Метод для получения списка всех бронирований (Read all)
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT book_ref, book_date, total_amount FROM bookings";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookRef(rs.getInt("book_ref"));
                booking.setBookDate(rs.getTimestamp("book_date"));
                booking.setTotalAmount(rs.getInt("total_amount"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении списка бронирований: " + e.getMessage());
        }
        return bookings;
    }
}
