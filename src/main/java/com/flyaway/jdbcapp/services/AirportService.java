package com.flyaway.jdbcapp.services;

import com.flyaway.jdbcapp.dao.AirportDao;
import com.flyaway.jdbcapp.entity.Airport;
import java.util.List;
import java.util.Scanner;

public class AirportService {
    private final AirportDao airportDao;
    private final Scanner scanner;

    public AirportService(AirportDao airportDao, Scanner scanner) {
        this.airportDao = airportDao;
        this.scanner = scanner;
    }

    public void listAllAirports() {
        List<Airport> airports = airportDao.getAllAirports();
        if (airports.isEmpty()) {
            System.out.println("Нет данных об аэропортах.");
        } else {
            System.out.println("\nСписок аэропортов:");
            airports.forEach(System.out::println);
        }
    }
}