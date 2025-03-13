package com.flyaway.jdbcapp;

import com.flyaway.jdbcapp.dao.*;
import com.flyaway.jdbcapp.entity.*;
import com.flyaway.jdbcapp.services.CRUDService;

import java.util.List;
import java.util.Scanner;

public class JdbcConsoleApp {

    private static BookingDao bookingDao = new BookingDao();
    private static AircraftDao aircraftDao = new AircraftDao();
    private static AirportDao airportDao = new AirportDao();
    private static FlightDao flightDao = new FlightDao();
    private static RouteDao routeDao = new RouteDao();
    private static Scanner scanner = new Scanner(System.in);
    private static CRUDService crudService = new CRUDService(bookingDao, scanner);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    manageBookingsMenu();
                    break;
                case "2":
                    listAircrafts();
                    break;
                case "3":
                    listAirports();
                    break;
                case "4":
                    listFlights();
                    break;
                case "5":
                    listRoutes();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
        System.out.println("Приложение завершено.");
    }

    private static void showMenu() {
        System.out.println("\n==================================");
        System.out.println(" FlyAway Sales JDBC Console App ");
        System.out.println("==================================");
        System.out.println("1. Управление бронированиями (CRUD)");
        System.out.println("2. Показать список самолетов");
        System.out.println("3. Показать список аэропортов");
        System.out.println("4. Показать список рейсов (flights_v)");
        System.out.println("5. Показать список маршрутов (routes)");
        System.out.println("0. Выход");
        System.out.print("Ваш выбор: ");
    }

    private static void manageBookingsMenu() {
        boolean inBookingMenu = true;
        while (inBookingMenu) {
            System.out.println("\n--- Управление бронированиями ---");
            System.out.println("1. Создать бронирование");
            System.out.println("2. Получить бронирование по book_ref");
            System.out.println("3. Обновить бронирование");
            System.out.println("4. Удалить бронирование");
            System.out.println("5. Показать все бронирования");
            System.out.println("0. Назад в главное меню");
            System.out.print("Ваш выбор: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    crudService.createBooking();
                    break;
                case "2":
                    crudService.getBooking();
                    break;
                case "3":
                    crudService.updateBooking();
                    break;
                case "4":
                    crudService.deleteBooking();
                    break;
                case "5":
                    crudService.listAllBookings();
                    break;
                case "0":
                    inBookingMenu = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void listAircrafts() {
        List<Aircraft> aircrafts = aircraftDao.getAllAircrafts();
        if (aircrafts.isEmpty()) {
            System.out.println("Нет данных о самолетах.");
        } else {
            System.out.println("Список самолетов:");
            aircrafts.forEach(System.out::println);
        }
    }

    private static void listAirports() {
        List<Airport> airports = airportDao.getAllAirports();
        if (airports.isEmpty()) {
            System.out.println("Нет данных об аэропортах.");
        } else {
            System.out.println("Список аэропортов:");
            airports.forEach(System.out::println);
        }
    }

    private static void listFlights() {
        List<Flight> flights = flightDao.getAllFlights();
        if (flights.isEmpty()) {
            System.out.println("Нет данных о рейсах.");
        } else {
            System.out.println("Список рейсов:");
            flights.forEach(System.out::println);
        }
    }

    private static void listRoutes() {
        List<Route> routes = routeDao.getAllRoutes();
        if (routes.isEmpty()) {
            System.out.println("Нет данных о маршрутах.");
        } else {
            System.out.println("Список маршрутов:");
            routes.forEach(System.out::println);
        }
    }
}