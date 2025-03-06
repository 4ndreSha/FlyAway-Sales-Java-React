package com.flyaway.jdbcapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDao {
    public List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<>();
        String sql = "SELECT flight_no, departure_airport, departure_airport_name, departure_city, " +
                "arrival_airport, arrival_airport_name, arrival_city, aircraft_code, duration, days_of_week " +
                "FROM routes";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Route route = new Route();
                route.setFlightNo(rs.getString("flight_no"));
                route.setDepartureAirport(rs.getString("departure_airport"));
                route.setDepartureAirportName(rs.getString("departure_airport_name"));
                route.setDepartureCity(rs.getString("departure_city"));
                route.setArrivalAirport(rs.getString("arrival_airport"));
                route.setArrivalAirportName(rs.getString("arrival_airport_name"));
                route.setArrivalCity(rs.getString("arrival_city"));
                route.setAircraftCode(rs.getString("aircraft_code"));
                route.setDuration(rs.getString("duration"));
                route.setDaysOfWeek(rs.getString("days_of_week"));
                routes.add(route);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении данных о маршрутах: " + e.getMessage());
        }
        return routes;
    }
}
