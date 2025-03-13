package com.flyaway.dao;

import com.flyaway.entity.Booking;
import com.flyaway.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {

    // Создание бронирования
    public boolean createBooking(Booking booking) {
        String sql = "INSERT INTO bookings(book_ref, book_date, total_amount, status, flight_id, passenger_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, booking.getBookRef());
            ps.setTimestamp(2, booking.getBookDate());
            ps.setBigDecimal(3, booking.getTotalAmount());
            ps.setString(4, booking.getStatus());
            ps.setInt(5, booking.getFlightId());
            ps.setInt(6, booking.getPassengerId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка создания бронирования: " + e.getMessage());
            return false;
        }
    }

    // Получение бронирования по PNR (book_ref)
    public Booking getBooking(String bookRef) {
        String sql = "SELECT * FROM bookings WHERE book_ref = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bookRef);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookRef(rs.getString("book_ref"));
                    booking.setBookDate(rs.getTimestamp("book_date"));
                    booking.setTotalAmount(rs.getBigDecimal("total_amount"));
                    booking.setStatus(rs.getString("status"));
                    booking.setFlightId(rs.getInt("flight_id"));
                    booking.setPassengerId(rs.getInt("passenger_id"));
                    // Дополнительно можно формировать flightDetails
                    return booking;
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка получения бронирования: " + e.getMessage());
        }
        return null;
    }

    // Получение бронирований по идентификатору пассажира
    public List<Booking> getBookingsByPassenger(int passengerId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE passenger_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, passengerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookRef(rs.getString("book_ref"));
                    booking.setBookDate(rs.getTimestamp("book_date"));
                    booking.setTotalAmount(rs.getBigDecimal("total_amount"));
                    booking.setStatus(rs.getString("status"));
                    booking.setFlightId(rs.getInt("flight_id"));
                    booking.setPassengerId(rs.getInt("passenger_id"));
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка получения бронирований: " + e.getMessage());
        }
        return bookings;
    }

    // Отмена бронирования
    public boolean cancelBooking(String bookRef) {
        String sql = "UPDATE bookings SET status = ? WHERE book_ref = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "CANCELLED");
            ps.setString(2, bookRef);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка отмены бронирования: " + e.getMessage());
            return false;
        }
    }
    // Метод для создания новой записи (Create)
    public boolean createBooking(String bookRef, Timestamp bookDate, BigDecimal totalAmount) {
        String sql = "INSERT INTO bookings(book_ref, book_date, total_amount) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bookRef);
            pstmt.setTimestamp(2, bookDate);
            pstmt.setBigDecimal(3, totalAmount);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при создании бронирования: " + e.getMessage());
        }
        return false;
    }

    // Метод для обновления бронирования (Update)
    public boolean updateBooking(String bookRef, Timestamp newDate, BigDecimal newTotalAmount) {
        String sql = "UPDATE bookings SET book_date = ?, total_amount = ? WHERE book_ref = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, newDate);
            pstmt.setBigDecimal(2, newTotalAmount);
            pstmt.setString(3, bookRef);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении бронирования: " + e.getMessage());
        }
        return false;
    }

    // Метод для удаления бронирования (Delete)
    public boolean deleteBooking(String bookRef) {
        String sql = "DELETE FROM bookings WHERE book_ref = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bookRef);
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
                booking.setBookRef(rs.getString("book_ref"));
                booking.setBookDate(rs.getTimestamp("book_date"));
                booking.setTotalAmount(rs.getBigDecimal("total_amount"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении списка бронирований: " + e.getMessage());
        }
        return bookings;
    }
}
