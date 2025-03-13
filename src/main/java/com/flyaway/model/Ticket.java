package com.flyaway.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Ticket {
    private String ticketNo;
    private String bookRef;
    private String contactData;
    private int passengerId;

    // Новые поля из bookings
    private Timestamp bookDate;
    private BigDecimal totalAmount;

    // Геттеры и сеттеры
    public String getTicketNo() {
        return ticketNo;
    }
    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }
    public String getBookRef() {
        return bookRef;
    }
    public void setBookRef(String bookRef) {
        this.bookRef = bookRef;
    }
    public String getContactData() {
        return contactData;
    }
    public void setContactData(String contactData) {
        this.contactData = contactData;
    }
    public int getPassengerId() {
        return passengerId;
    }
    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }
    public Timestamp getBookDate() {
        return bookDate;
    }
    public void setBookDate(Timestamp bookDate) {
        this.bookDate = bookDate;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
