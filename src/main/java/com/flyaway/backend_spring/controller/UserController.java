package com.flyaway.backend_spring.controller;

import com.flyaway.backend_spring.dto.TicketBookingInfo;
import com.flyaway.backend_spring.dto.UserUpdateRequest;
import com.flyaway.backend_spring.entity.Passenger;
import com.flyaway.backend_spring.dto.TicketInfo;
import com.flyaway.backend_spring.repository.PassengerRepository;
import com.flyaway.backend_spring.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/info")
    public Optional<Passenger> getUserInfo(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("PASSENGER_ID");
        return passengerRepository.findById(userId);
    }

    @GetMapping("/tickets")
    public List<TicketBookingInfo> getUserTickets(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("PASSENGER_ID");
        return ticketRepository.findTicketsByPassengerId(userId);
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody UserUpdateRequest request, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("PASSENGER_ID");
        if (userId == null) {
            return "User not authenticated";
        }

        Optional<Passenger> optionalPassenger = passengerRepository.findById(userId);
        if (optionalPassenger.isPresent()) {
            Passenger passenger = optionalPassenger.get();
            passenger.setEmail(request.getEmail());
            passenger.setFirstName(request.getFirstName());
            passenger.setLastName(request.getLastName());
            passenger.setPatronymic(request.getPatronymic());
            passenger.setPhone(request.getPhone());
            passengerRepository.save(passenger);
            return "User updated successfully";
        }
        return "User not found";
    }
}
