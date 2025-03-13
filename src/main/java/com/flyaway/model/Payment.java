package com.flyaway.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Payment {
    private int id;
    private String bookingRef; // связь с Booking.bookRef
    private BigDecimal amount;
    private Timestamp paymentDate;
    private String paymentMethod; // card, paypal, wallet и т.д.
    private String status;        // PAID, FAILED, PENDING

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBookingRef() {
        return bookingRef;
    }
    public void setBookingRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Timestamp getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
