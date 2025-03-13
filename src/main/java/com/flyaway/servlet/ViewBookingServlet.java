package com.flyaway.servlet;

import com.flyaway.dao.BookingDao;
import com.flyaway.model.Booking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/viewBooking")
public class ViewBookingServlet extends HttpServlet {
    private BookingDao bookingDao = new BookingDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pnr = req.getParameter("pnr");
        if (pnr == null) {
            resp.sendRedirect("account");
            return;
        }
        Booking booking = bookingDao.getBooking(pnr);
        if (booking != null) {
            req.setAttribute("booking", booking);
            req.getRequestDispatcher("pages/viewBooking.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Бронирование не найдено");
            req.getRequestDispatcher("pages/account.jsp").forward(req, resp);
        }
    }
}
