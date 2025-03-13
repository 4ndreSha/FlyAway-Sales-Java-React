package com.flyaway.jdbcapp.dao;

import com.flyaway.jdbcapp.services.DBConnection;
import com.flyaway.jdbcapp.entity.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDao {
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT flight_id, flight_no, scheduled_departure, scheduled_departure_local, " +
                "scheduled_arrival, scheduled_arrival_local, scheduled_duration, departure_airport, " +
                "departure_airport_name, departure_city, arrival_airport, arrival_airport_name, arrival_city, " +
                "status, aircraft_code, actual_departure, actual_departure_local, actual_arrival, " +
                "actual_arrival_local, actual_duration FROM flights_v";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("flight_id"));
                flight.setFlightNo(rs.getString("flight_no"));
                flight.setScheduledDeparture(rs.getTimestamp("scheduled_departure"));
                flight.setScheduledDepartureLocal(rs.getTimestamp("scheduled_departure_local"));
                flight.setScheduledArrival(rs.getTimestamp("scheduled_arrival"));
                flight.setScheduledArrivalLocal(rs.getTimestamp("scheduled_arrival_local"));
                flight.setScheduledDuration(rs.getString("scheduled_duration"));
                flight.setDepartureAirport(rs.getString("departure_airport"));
                flight.setDepartureAirportName(rs.getString("departure_airport_name"));
                flight.setDepartureCity(rs.getString("departure_city"));
                flight.setArrivalAirport(rs.getString("arrival_airport"));
                flight.setArrivalAirportName(rs.getString("arrival_airport_name"));
                flight.setArrivalCity(rs.getString("arrival_city"));
                flight.setStatus(rs.getString("status"));
                flight.setAircraftCode(rs.getString("aircraft_code"));
                flight.setActualDeparture(rs.getTimestamp("actual_departure"));
                flight.setActualDepartureLocal(rs.getTimestamp("actual_departure_local"));
                flight.setActualArrival(rs.getTimestamp("actual_arrival"));
                flight.setActualArrivalLocal(rs.getTimestamp("actual_arrival_local"));
                flight.setActualDuration(rs.getString("actual_duration"));
                flights.add(flight);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении данных о рейсах: " + e.getMessage());
        }
        return flights;
    }
}
