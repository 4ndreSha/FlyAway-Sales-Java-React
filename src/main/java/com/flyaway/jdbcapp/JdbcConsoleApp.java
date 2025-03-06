package com.flyaway.jdbcapp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class JdbcConsoleApp {

    private static BookingDao bookingDao = new BookingDao();
    private static AircraftDao aircraftDao = new AircraftDao();
    private static AirportDao airportDao = new AirportDao();
    private static FlightDao flightDao = new FlightDao();
    private static RouteDao routeDao = new RouteDao();
    private static Scanner scanner = new Scanner(System.in);

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

    // Подменю для бронирований (CRUD)
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
                    createBooking();
                    break;
                case "2":
                    getBooking();
                    break;
                case "3":
                    updateBooking();
                    break;
                case "4":
                    deleteBooking();
                    break;
                case "5":
                    listAllBookings();
                    break;
                case "0":
                    inBookingMenu = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // CRUD-операции для бронирований (как реализовано ранее)
    private static void createBooking() {
        try {
            System.out.print("Введите book_ref: ");
            String bookRef = scanner.nextLine().trim();
            Timestamp bookDate = new Timestamp(System.currentTimeMillis());
            System.out.print("Введите total_amount: ");
            BigDecimal totalAmount = new BigDecimal(scanner.nextLine().trim());
            if (bookingDao.createBooking(bookRef, bookDate, totalAmount)) {
                System.out.println("Бронирование успешно создано.");
            } else {
                System.out.println("Ошибка при создании бронирования.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка ввода: " + e.getMessage());
        }
    }

    private static void getBooking() {
        System.out.print("Введите book_ref для поиска: ");
        String bookRef = scanner.nextLine().trim();
        Booking booking = bookingDao.getBooking(bookRef);
        if (booking != null) {
            System.out.println("Найдено: " + booking);
        } else {
            System.out.println("Бронирование не найдено.");
        }
    }

    private static void updateBooking() {
        try {
            System.out.print("Введите book_ref для обновления: ");
            String bookRef = scanner.nextLine().trim();
            Timestamp newDate = new Timestamp(System.currentTimeMillis());
            System.out.print("Введите новое total_amount: ");
            BigDecimal newTotalAmount = new BigDecimal(scanner.nextLine().trim());
            if (bookingDao.updateBooking(bookRef, newDate, newTotalAmount)) {
                System.out.println("Бронирование успешно обновлено.");
            } else {
                System.out.println("Ошибка при обновлении бронирования.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка ввода: " + e.getMessage());
        }
    }

    private static void deleteBooking() {
        System.out.print("Введите book_ref для удаления: ");
        String bookRef = scanner.nextLine().trim();
        if (bookingDao.deleteBooking(bookRef)) {
            System.out.println("Бронирование успешно удалено.");
        } else {
            System.out.println("Ошибка при удалении бронирования.");
        }
    }

    private static void listAllBookings() {
        List<Booking> bookings = bookingDao.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("Нет доступных бронирований.");
        } else {
            System.out.println("Список бронирований:");
            bookings.forEach(System.out::println);
        }
    }

    // Методы для работы с представлениями

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
