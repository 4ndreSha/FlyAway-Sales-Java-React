package com.flyaway.dao;

import com.flyaway.model.Flight;
import com.flyaway.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDao {

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights_v";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                flights.add(extractFlight(rs));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении рейсов: " + e.getMessage());
        }
        return flights;
    }

    public Flight getFlightById(int flightId) {
        String sql = "SELECT * FROM flights_v WHERE flight_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, flightId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractFlight(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении данных о рейсе: " + e.getMessage());
        }
        return null;
    }

    public List<Flight> getFlightsByAirport(String airportName) {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights_v " +
                "WHERE departure_airport_name ILIKE ? " +
                "   OR arrival_airport_name ILIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            // Передаём параметр так, чтобы искались полные совпадения
            ps.setString(1, airportName);
            ps.setString(2, airportName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    flights.add(extractFlight(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка поиска рейсов по аэропорту: " + e.getMessage());
        }
        return flights;
    }

    public List<Flight> searchFlights(String departure, String arrival, Timestamp departureDate) {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights_v WHERE departure_airport = ? AND arrival_airport = ? AND DATE(scheduled_departure) = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, departure);
            ps.setString(2, arrival);
            ps.setDate(3, new java.sql.Date(departureDate.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    flights.add(extractFlight(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка поиска рейсов: " + e.getMessage());
        }
        return flights;
    }

    public List<Flight> getFlightsByLocation(String location) {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights_v " +
                "WHERE departure_airport_name ILIKE ? " +
                "   OR arrival_airport_name ILIKE ? " +
                "   OR departure_city ILIKE ? " +
                "   OR arrival_city ILIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String param = "%" + location + "%";
            ps.setString(1, param);
            ps.setString(2, param);
            ps.setString(3, param);
            ps.setString(4, param);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    flights.add(extractFlight(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка поиска рейсов по местоположению: " + e.getMessage());
        }
        return flights;
    }

    public List<Flight> getFlightsByDeparture(String airportName, String timeSort, String statusFilter) {
        List<Flight> flights = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM flights_v WHERE departure_airport_name = ?");
        if (statusFilter != null && !statusFilter.equalsIgnoreCase("All")) {
            sql.append(" AND status = ?");
        }
        if ("time_asc".equalsIgnoreCase(timeSort)) {
            sql.append(" ORDER BY scheduled_departure ASC");
        } else if ("time_desc".equalsIgnoreCase(timeSort)) {
            sql.append(" ORDER BY scheduled_departure DESC");
        } else {
            // Значение по умолчанию – по возрастанию времени
            sql.append(" ORDER BY scheduled_departure ASC");
        }
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            ps.setString(1, airportName);
            if (statusFilter != null && !statusFilter.equalsIgnoreCase("All")) {
                ps.setString(2, statusFilter);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    flights.add(extractFlight(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка поиска рейсов по отправлению: " + e.getMessage());
        }
        return flights;
    }

    // Метод для поиска рейсов по прибытии с сортировкой и фильтром по статусу
    public List<Flight> getFlightsByArrival(String airportName, String timeSort, String statusFilter) {
        List<Flight> flights = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM flights_v WHERE arrival_airport_name = ?");
        if (statusFilter != null && !statusFilter.equalsIgnoreCase("All")) {
            sql.append(" AND status = ?");
        }
        if ("time_asc".equalsIgnoreCase(timeSort)) {
            sql.append(" ORDER BY scheduled_arrival ASC");
        } else if ("time_desc".equalsIgnoreCase(timeSort)) {
            sql.append(" ORDER BY scheduled_arrival DESC");
        } else {
            sql.append(" ORDER BY scheduled_arrival ASC");
        }
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            ps.setString(1, airportName);
            if (statusFilter != null && !statusFilter.equalsIgnoreCase("All")) {
                ps.setString(2, statusFilter);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    flights.add(extractFlight(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка поиска рейсов по прибытии: " + e.getMessage());
        }
        return flights;
    }

    private Flight extractFlight(ResultSet rs) throws SQLException {
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
        flight.setAirline("FlyAway Inc.");
        return flight;
    }
}


