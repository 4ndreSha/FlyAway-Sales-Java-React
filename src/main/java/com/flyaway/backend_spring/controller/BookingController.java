package com.flyaway.backend_spring.controller;

import com.flyaway.backend_spring.dto.BookingRequest;
import com.flyaway.backend_spring.entity.Booking;
import com.flyaway.backend_spring.entity.Ticket;
import com.flyaway.backend_spring.repository.BookingRepository;
import com.flyaway.backend_spring.repository.TicketRepository;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private static final String PASSENGER_ID_ATTRIBUTE = "PASSENGER_ID"; // Constant for session key

    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;

    public BookingController(
            BookingRepository bookingRepository,
            TicketRepository ticketRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.ticketRepository = ticketRepository;
    }

    @PostMapping("/create")
    public String createBooking(@RequestBody BookingRequest request, HttpSession session) {
        Integer passengerId = (Integer) session.getAttribute(PASSENGER_ID_ATTRIBUTE);
        if (passengerId == null) {
            return "User not authenticated";
        }

        // Create booking
        Booking booking = new Booking();
        String bookRef = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        booking.setBookRef(bookRef);
        booking.setBookDate(ZonedDateTime.now());
        booking.setTotalAmount(100.0); // Fixed amount for simplicity
        bookingRepository.save(booking);

        // Create ticket
        Ticket ticket = new Ticket();
        String ticketNo = UUID.randomUUID().toString().replace("-", "").substring(0, 13).toUpperCase();
        ticket.setTicketNo(ticketNo);
        ticket.setBookRef(bookRef);
        ticket.setPassengerId(passengerId);
        ticketRepository.save(ticket);

        return "Booking and Ticket created successfully";
    }
}