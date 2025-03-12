package com.flyaway.servlet;

import com.flyaway.dao.BookingDao;
import com.flyaway.model.Booking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookings")
public class BookingsServlet extends HttpServlet {
    private BookingDao bookingDao = new BookingDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Booking> bookings = bookingDao.getAllBookings();
        req.setAttribute("bookings", bookings);
        req.getRequestDispatcher("pages/bookings.jsp").forward(req, resp);
    }
}
