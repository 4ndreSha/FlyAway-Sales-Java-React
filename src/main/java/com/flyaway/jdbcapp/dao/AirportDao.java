package com.flyaway.jdbcapp.dao;

import com.flyaway.jdbcapp.entity.Airport;
import com.flyaway.jdbcapp.services.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportDao {
    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<>();
        String sql = "SELECT airport_code, airport_name, city, latitude, longitude, timezone FROM airports";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Airport airport = new Airport();
                airport.setAirportCode(rs.getInt("airport_code"));
                airport.setAirportName(rs.getString("airport_name"));
                airport.setCity(rs.getString("city"));
                airport.setLatitude(rs.getDouble("latitude"));
                airport.setLongitude(rs.getDouble("longitude"));
                airport.setTimezone(rs.getString("timezone"));
                airports.add(airport);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении данных об аэропортах: " + e.getMessage());
        }
        return airports;
    }
}
