package com.flyaway.backend_spring.controller;

import com.flyaway.backend_spring.dto.RegistrationRequest;
import com.flyaway.backend_spring.dto.LoginRequest;
import com.flyaway.backend_spring.entity.Passenger;
import com.flyaway.backend_spring.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.*;
import java.util.Optional;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PassengerRepository passengerRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/status")
    public Map<String, Boolean> authStatus(HttpSession session) {
        boolean isAuthenticated = session.getAttribute("PASSENGER_ID") != null;
        return Collections.singletonMap("authenticated", isAuthenticated);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest request, HttpSession session) {
        if (passengerRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }
        Passenger passenger = new Passenger();
        passenger.setEmail(request.getEmail());
        passenger.setPassword(passwordEncoder.encode(request.getPassword()));
        passenger.setFirstName(request.getFirstName());
        passenger.setLastName(request.getLastName());
        passenger.setPatronymic(request.getPatronymic());
        passenger.setPhone(request.getPhone());
        passengerRepository.save(passenger);
        session.setAttribute("PASSENGER_ID", passenger.getId());
        return "Registration successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request, HttpSession session) {
        Optional<Passenger> opt = passengerRepository.findByEmail(request.getEmail());
        if (opt.isPresent()) {
            Passenger passenger = opt.get();
            if (passwordEncoder.matches(request.getPassword(), passenger.getPassword())) {
                session.setAttribute("PASSENGER_ID", passenger.getId());
                return "Login successful";
            }
        }
        return "Invalid email or password";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out successfully";
    }
}
