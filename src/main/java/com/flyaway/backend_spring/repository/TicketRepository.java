package com.flyaway.backend_spring.repository;

import com.flyaway.backend_spring.dto.TicketBookingInfo;
import com.flyaway.backend_spring.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    @Query("SELECT NEW com.flyaway.backend_spring.dto.TicketBookingInfo(" +
            "t.ticketNo, t.bookRef, t.contactData, t.passengerId, " +
            "t.booking.bookDate, t.booking.totalAmount) " +
            "FROM Ticket t " +
            "WHERE t.passengerId = :passengerId")
    List<TicketBookingInfo> findTicketsByPassengerId(@Param("passengerId") Integer passengerId);
}