package com.flyaway.servlet;

import com.flyaway.dao.BookingDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/cancelBooking")
public class CancelBookingServlet extends HttpServlet {
    private BookingDao bookingDao = new BookingDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pnr = req.getParameter("pnr");
        if (pnr != null) {
            boolean cancelled = bookingDao.cancelBooking(pnr);
            if (cancelled) {
                req.setAttribute("message", "Бронирование отменено");
            } else {
                req.setAttribute("error", "Ошибка отмены бронирования");
            }
        }
        req.getRequestDispatcher("account").forward(req, resp);
    }
}
