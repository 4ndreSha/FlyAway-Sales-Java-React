package com.flyaway.dao;

import com.flyaway.entity.Payment;
import com.flyaway.util.DBConnection;

import java.sql.*;

public class PaymentDao {

    public boolean processPayment(Payment payment) {
        String sql = "INSERT INTO payment(booking_ref, amount, payment_date, payment_method, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, payment.getBookingRef());
            ps.setBigDecimal(2, payment.getAmount());
            ps.setTimestamp(3, payment.getPaymentDate());
            ps.setString(4, payment.getPaymentMethod());
            ps.setString(5, payment.getStatus());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) return false;
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    payment.setId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Ошибка обработки платежа: " + e.getMessage());
            return false;
        }
    }
}
