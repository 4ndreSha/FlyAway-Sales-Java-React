package com.flyaway.jdbcapp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Booking {
    private String bookRef;
    private Timestamp bookDate;
    private BigDecimal totalAmount;

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

    @Override
    public String toString() {
        return "Booking{" +
                "bookRef='" + bookRef + '\'' +
                ", bookDate=" + bookDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
