package com.flyaway.dao;

import com.flyaway.model.Aircraft;
import com.flyaway.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AircraftDao {
    public List<Aircraft> getAllAircrafts() {
        List<Aircraft> aircrafts = new ArrayList<>();
        String sql = "SELECT aircraft_code, model, range FROM aircrafts";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aircraft aircraft = new Aircraft();
                aircraft.setAircraftCode(rs.getString("aircraft_code"));
                aircraft.setModel(rs.getString("model"));
                aircraft.setRange(rs.getInt("range"));
                aircrafts.add(aircraft);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении данных о самолетах: " + e.getMessage());
        }
        return aircrafts;
    }
}
