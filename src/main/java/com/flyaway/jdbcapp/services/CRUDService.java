package com.flyaway.jdbcapp.services;

import com.flyaway.jdbcapp.dao.BookingDao;
import com.flyaway.jdbcapp.entity.Booking;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class CRUDService {
    private final BookingDao bookingDao;
    private final Scanner scanner;

    public CRUDService(BookingDao bookingDao, Scanner scanner) {
        this.bookingDao = bookingDao;
        this.scanner = scanner;
    }

    public void createBooking() {
        try {
            System.out.print("Введите book_ref: ");
            int bookRef = Integer.parseInt(scanner.nextLine().trim());
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

    public void getBooking() {
        System.out.print("Введите book_ref для поиска: ");
        int bookRef = Integer.parseInt(scanner.nextLine().trim());
        Booking booking = bookingDao.getBooking(bookRef);

        if (booking != null) {
            System.out.println("Найдено: " + booking);
        } else {
            System.out.println("Бронирование не найдено.");
        }
    }

    public void updateBooking() {
        try {
            System.out.print("Введите book_ref для обновления: ");
            int bookRef = Integer.parseInt(scanner.nextLine().trim());
            Timestamp newDate = new Timestamp(System.currentTimeMillis());
            System.out.print("Введите новое total_amount: ");
            int newTotalAmount = Integer.parseInt(scanner.nextLine().trim());

            if (bookingDao.updateBooking(bookRef, newDate, newTotalAmount)) {
                System.out.println("Бронирование успешно обновлено.");
            } else {
                System.out.println("Ошибка при обновлении бронирования.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка ввода: " + e.getMessage());
        }
    }

    public void deleteBooking() {
        System.out.print("Введите book_ref для удаления: ");
        int bookRef = Integer.parseInt(scanner.nextLine().trim());

        if (bookingDao.deleteBooking(bookRef)) {
            System.out.println("Бронирование успешно удалено.");
        } else {
            System.out.println("Ошибка при удалении бронирования.");
        }
    }

    public void listAllBookings() {
        List<Booking> bookings = bookingDao.getAllBookings();

        if (bookings.isEmpty()) {
            System.out.println("Нет доступных бронирований.");
        } else {
            System.out.println("Список бронирований:");
            bookings.forEach(System.out::println);
        }
    }
}