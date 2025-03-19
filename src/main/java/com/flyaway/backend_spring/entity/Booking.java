package com.flyaway.backend_spring.entity;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "bookings", schema = "bookings")
public class Booking {

    @Id
    @Column(name = "book_ref", length = 6, nullable = false, unique = true)
    private String bookRef;

    @Column(name = "book_date", nullable = false)
    private ZonedDateTime bookDate;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    // Геттеры и сеттеры
    public String getBookRef() { return bookRef; }

    public void setBookRef(String bookRef) { this.bookRef = bookRef; }

    public ZonedDateTime getBookDate() { return bookDate; }

    public void setBookDate(ZonedDateTime bookDate) { this.bookDate = bookDate; }

    public Double getTotalAmount() { return totalAmount; }

    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
}