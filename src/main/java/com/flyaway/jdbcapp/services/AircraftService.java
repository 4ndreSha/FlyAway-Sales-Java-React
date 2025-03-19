package com.flyaway.jdbcapp.services;

import com.flyaway.jdbcapp.dao.AircraftDao;
import com.flyaway.jdbcapp.entity.Aircraft;
import java.util.List;
import java.util.Scanner;

public class AircraftService {
    private final AircraftDao aircraftDao;
    private final Scanner scanner;

    public AircraftService(AircraftDao aircraftDao, Scanner scanner) {
        this.aircraftDao = aircraftDao;
        this.scanner = scanner;
    }

    public void listAllAircrafts() {
        List<Aircraft> aircrafts = aircraftDao.getAllAircrafts();
        if (aircrafts.isEmpty()) {
            System.out.println("Нет данных о самолетах.");
        } else {
            System.out.println("\nСписок самолетов:");
            aircrafts.forEach(System.out::println);
        }
    }
}