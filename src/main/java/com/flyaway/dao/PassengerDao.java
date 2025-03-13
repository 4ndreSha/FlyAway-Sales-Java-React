package com.flyaway.dao;

import com.flyaway.entity.Passenger;
import com.flyaway.util.DBConnection;

import java.sql.*;

public class PassengerDao {

    // Регистрация нового пользователя
    public boolean register(Passenger passenger) {
        String sql = "INSERT INTO passenger(email, password, first_name, last_name, patronymic, phone) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, passenger.getEmail());
            ps.setString(2, passenger.getPassword()); // В реальном приложении пароль необходимо захешировать
            ps.setString(3, passenger.getFirstName());
            ps.setString(4, passenger.getLastName());
            ps.setString(5, passenger.getPatronymic());
            ps.setString(6, passenger.getPhone());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    passenger.setId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Ошибка регистрации: " + e.getMessage());
            return false;
        }
    }

    // Аутентификация пользователя
    public Passenger authenticate(String email, String password) {
        String sql = "SELECT * FROM passenger WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password); // В реальном приложении сравнение должно выполняться по хешу
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Passenger passenger = new Passenger();
                    passenger.setId(rs.getInt("id"));
                    passenger.setEmail(rs.getString("email"));
                    passenger.setPassword(rs.getString("password"));
                    passenger.setFirstName(rs.getString("first_name"));
                    passenger.setLastName(rs.getString("last_name"));
                    passenger.setPatronymic(rs.getString("patronymic"));
                    passenger.setPhone(rs.getString("phone"));
                    return passenger;
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка аутентификации: " + e.getMessage());
        }
        return null;
    }

    public boolean updatePassenger(Passenger passenger) {
        String sql = "UPDATE passenger SET email = ?, first_name = ?, last_name = ?, patronymic = ?, phone = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, passenger.getEmail());
            ps.setString(2, passenger.getFirstName());
            ps.setString(3, passenger.getLastName());
            ps.setString(4, passenger.getPatronymic());
            ps.setString(5, passenger.getPhone());
            ps.setInt(6, passenger.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка обновления пассажира: " + e.getMessage());
            return false;
        }
    }

    // Метод для получения информации о забронированных билетах пассажира можно реализовать через join с таблицей tickets
}
