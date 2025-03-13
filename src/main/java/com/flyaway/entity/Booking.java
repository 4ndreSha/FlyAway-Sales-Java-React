package com.flyaway.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Booking {
    private String bookRef;  // уникальный идентификатор бронирования (PNR)
    private Timestamp bookDate;
    private BigDecimal totalAmount;
    private String status;   // например: PENDING, CONFIRMED, CANCELLED
    private int flightId;    // идентификатор рейса
    private int passengerId; // идентификатор пассажира

    // Дополнительное поле для отображения деталей рейса (можно формировать через JOIN)
    private String flightDetails;

    // Геттеры и сеттеры
    public String getBookRef() {
        return bookRef;
    }
    public void setBookRef(String bookRef) {
        this.bookRef = bookRef;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getFlightId() {
        return flightId;
    }
    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
    public int getPassengerId() {
        return passengerId;
    }
    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }
    public String getFlightDetails() {
        return flightDetails;
    }
    public void setFlightDetails(String flightDetails) {
        this.flightDetails = flightDetails;
    }
}
