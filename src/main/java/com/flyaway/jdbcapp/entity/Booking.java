package com.flyaway.jdbcapp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Booking {
    private int bookRef;
    private Timestamp bookDate;
    private int totalAmount;

    public int getBookRef() {
        return bookRef;
    }
    public void setBookRef(int bookRef) {
        this.bookRef = bookRef;
    }
    public int getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Timestamp getBookDate() {
        return bookDate;
    }

    public void setBookDate(Timestamp bookDate) {
        this.bookDate = bookDate;
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
