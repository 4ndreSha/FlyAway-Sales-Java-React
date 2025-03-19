package com.flyaway.backend_spring.controller;

import com.flyaway.backend_spring.dto.TicketBookingInfo;
import com.flyaway.backend_spring.dto.UserUpdateRequest;
import com.flyaway.backend_spring.entity.Passenger;
import com.flyaway.backend_spring.repository.PassengerRepository;
import com.flyaway.backend_spring.repository.TicketRepository;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final String PASSENGER_ID_ATTRIBUTE = "PASSENGER_ID";

    private final PassengerRepository passengerRepository;
    private final TicketRepository ticketRepository;

    public UserController(PassengerRepository passengerRepository,
                          TicketRepository ticketRepository) {
        this.passengerRepository = passengerRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/info")
    public Optional<Passenger> getUserInfo(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(PASSENGER_ID_ATTRIBUTE);
        return passengerRepository.findById(userId);
    }

    @GetMapping("/tickets")
    public List<TicketBookingInfo> getUserTickets(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(PASSENGER_ID_ATTRIBUTE);
        return ticketRepository.findTicketsByPassengerId(userId);
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody UserUpdateRequest request, HttpSession session) {
        Integer userId = (Integer) session.getAttribute(PASSENGER_ID_ATTRIBUTE);
        if (userId == null) {
            return "User not authenticated";
        }

        return passengerRepository.findById(userId)
                .map(passenger -> {
                    passenger.setEmail(request.getEmail());
                    passenger.setFirstName(request.getFirstName());
                    passenger.setLastName(request.getLastName());
                    passenger.setPatronymic(request.getPatronymic());
                    passenger.setPhone(request.getPhone());
                    passengerRepository.save(passenger);
                    return "User updated successfully";
                })
                .orElse("User not found");
    }
}