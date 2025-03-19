package com.flyaway.backend_spring.controller;

import com.flyaway.backend_spring.dto.BookingRequest;
import com.flyaway.backend_spring.entity.Booking;
import com.flyaway.backend_spring.entity.Ticket;
import com.flyaway.backend_spring.entity.Passenger;
import com.flyaway.backend_spring.repository.BookingRepository;
import com.flyaway.backend_spring.repository.TicketRepository;
import com.flyaway.backend_spring.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @PostMapping("/create")
    public String createBooking(@RequestBody BookingRequest request, HttpSession session) {
        Integer passengerId = (Integer) session.getAttribute("PASSENGER_ID");
        if (passengerId == null) {
            return "User not authenticated";
        }

        // Создаем бронирование
        Booking booking = new Booking();
        // Генерируем уникальный book_ref (6 символов)
        String bookRef = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        booking.setBookRef(bookRef);
        booking.setBookDate(ZonedDateTime.now());
        // Здесь можно вычислить реальную стоимость, пока установим фиксированное значение
        booking.setTotalAmount(100.0);
        bookingRepository.save(booking);

        // Получаем информацию о пользователе для формирования contact_data
        Passenger passenger = passengerRepository.findById(passengerId).orElse(null);
        String contactData = String.format(
                "{\"email\": \"%s\", \"phone\": \"%s\"}",
                passenger.getEmail(),
                passenger.getPhone()
        );
        // Создаем билет, связанный с бронированием
        Ticket ticket = new Ticket();
        // Генерируем уникальный ticket_no (13 символов)
        String ticketNo = UUID.randomUUID().toString().replace("-", "").substring(0, 13).toUpperCase();
        ticket.setTicketNo(ticketNo);
        ticket.setBookRef(bookRef);
        ticket.setPassengerId(passengerId);
        //ticket.setContactData(contactData);
        ticketRepository.save(ticket);

        return "Booking and Ticket created successfully";
    }
}
