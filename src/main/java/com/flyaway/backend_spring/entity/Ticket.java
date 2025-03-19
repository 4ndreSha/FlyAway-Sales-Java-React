package com.flyaway.backend_spring.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "tickets", schema = "bookings")
public class Ticket {

    @Id
    @Column(name = "ticket_no", length = 13)
    private String ticketNo;

    @Column(name = "book_ref", length = 6, nullable = false)
    private String bookRef;

    @Column(name = "passenger_id")
    private Integer passengerId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "contact_data", columnDefinition = "jsonb")
    private String contactData;

    @ManyToOne
    @JoinColumn(name = "book_ref", referencedColumnName = "book_ref", insertable = false, updatable = false)
    private Booking booking;

    // Геттеры и сеттеры
    public String getTicketNo() { return ticketNo; }

    public void setTicketNo(String ticketNo) { this.ticketNo = ticketNo; }

    public String getBookRef() { return bookRef; }

    public void setBookRef(String bookRef) { this.bookRef = bookRef; }

    public Integer getPassengerId() { return passengerId; }

    public void setPassengerId(Integer passengerId) { this.passengerId = passengerId; }

    public String getContactData() { return contactData; }

    public void setContactData(String contactData) { this.contactData = contactData; }

    public Booking getBooking() { return booking; }

    public void setBooking(Booking booking) { this.booking = booking; }

}