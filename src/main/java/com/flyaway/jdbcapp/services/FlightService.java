package com.flyaway.jdbcapp.services;

import com.flyaway.jdbcapp.dao.FlightDao;
import com.flyaway.jdbcapp.entity.Flight;
import java.util.List;
import java.util.Scanner;

public class FlightService {
    private final FlightDao flightDao;
    private final Scanner scanner;

    public FlightService(FlightDao flightDao, Scanner scanner) {
        this.flightDao = flightDao;
        this.scanner = scanner;
    }

    public void listAllFlights() {
        List<Flight> flights = flightDao.getAllFlights();
        if (flights.isEmpty()) {
            System.out.println("Нет данных о рейсах.");
        } else {
            System.out.println("\nСписок рейсов:");
            flights.forEach(System.out::println);
        }
    }
}