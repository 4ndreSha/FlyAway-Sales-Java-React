package com.flyaway.jdbcapp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class JdbcConsoleApp {

    private static BookingDao bookingDao = new BookingDao();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            String choice = scanner.nextLine();

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
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        System.out.println("Приложение завершено.");
    }

    private static void showMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1. Создать бронирование");
        System.out.println("2. Получить бронирование по reference");
        System.out.println("3. Обновить бронирование");
        System.out.println("4. Удалить бронирование");
        System.out.println("5. Показать все бронирования");
        System.out.println("0. Выход");
        System.out.print("Ваш выбор: ");
    }

    private static void createBooking() {
        try {
            System.out.print("Введите book_ref: ");
            String bookRef = scanner.nextLine();

            // Для примера берём текущее время
            Timestamp bookDate = new Timestamp(System.currentTimeMillis());

            System.out.print("Введите total_amount: ");
            BigDecimal totalAmount = new BigDecimal(scanner.nextLine());

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
        String bookRef = scanner.nextLine();
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
            String bookRef = scanner.nextLine();

            // Новые данные
            Timestamp newDate = new Timestamp(System.currentTimeMillis());
            System.out.print("Введите новое total_amount: ");
            BigDecimal newTotalAmount = new BigDecimal(scanner.nextLine());

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
        String bookRef = scanner.nextLine();
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
}
