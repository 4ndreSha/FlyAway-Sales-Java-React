package com.flyaway.dao;

import com.flyaway.model.Airport;
import com.flyaway.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportDao {
    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<>();
        String sql = "SELECT airport_code, airport_name, city, coordinates, timezone FROM airports";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Airport airport = new Airport();
                airport.setAirportCode(rs.getString("airport_code"));
                airport.setAirportName(rs.getString("airport_name"));
                airport.setCity(rs.getString("city"));
                // Для поля coordinates можно использовать rs.getString()
                airport.setCoordinates(rs.getString("coordinates"));
                airport.setTimezone(rs.getString("timezone"));
                airports.add(airport);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении данных об аэропортах: " + e.getMessage());
        }
        return airports;
    }
}
