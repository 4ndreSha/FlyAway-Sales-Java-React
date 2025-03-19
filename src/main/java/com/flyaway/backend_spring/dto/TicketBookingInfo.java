package com.flyaway.backend_spring.dto;

import java.time.ZonedDateTime;

public class TicketBookingInfo {
    private String ticketNo;
    private String bookRef;
    private String contactData;
    private Integer passengerId;
    private ZonedDateTime bookDate;
    private Double totalAmount;

    public TicketBookingInfo(String ticketNo, String bookRef, String contactData,
                             Integer passengerId, ZonedDateTime bookDate, Double totalAmount) {
        this.ticketNo = ticketNo;
        this.bookRef = bookRef;
        this.contactData = contactData;
        this.passengerId = passengerId;
        this.bookDate = bookDate;
        this.totalAmount = totalAmount;
    }

    // Геттеры и сеттеры
    public String getTicketNo() { return ticketNo; }
    public String getBookRef() { return bookRef; }
    public String getContactData() { return contactData; }
    public Integer getPassengerId() { return passengerId; }
    public ZonedDateTime getBookDate() { return bookDate; }
    public Double getTotalAmount() { return totalAmount; }
    // Сеттеры при необходимости
}