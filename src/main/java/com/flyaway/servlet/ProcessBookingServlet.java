package com.flyaway.servlet;

import com.flyaway.dao.BookingDao;
import com.flyaway.model.Booking;
import com.flyaway.model.Passenger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

@WebServlet("/processBooking")
public class ProcessBookingServlet extends HttpServlet {
    private BookingDao bookingDao = new BookingDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Предполагается, что пользователь уже аутентифицирован и хранится в сессии
        Passenger passenger = (Passenger) req.getSession().getAttribute("passenger");
        if (passenger == null) {
            resp.sendRedirect("login");
            return;
        }

        // Получаем параметры бронирования: flightId, итоговая стоимость и т.д.
        String flightIdStr = req.getParameter("flightId");
        String totalAmountStr = req.getParameter("totalAmount");
        if (flightIdStr == null || totalAmountStr == null) {
            req.setAttribute("error", "Не все данные заполнены");
            req.getRequestDispatcher("pages/booking.jsp").forward(req, resp);
            return;
        }
        int flightId = Integer.parseInt(flightIdStr);
        BigDecimal totalAmount = new BigDecimal(totalAmountStr);

        Booking booking = new Booking();
        // Генерируем уникальный PNR (например, используя текущее время)
        booking.setBookRef("PNR" + System.currentTimeMillis());
        booking.setBookDate(new Timestamp(System.currentTimeMillis()));
        booking.setTotalAmount(totalAmount);
        booking.setStatus("PENDING");
        booking.setFlightId(flightId);
        booking.setPassengerId(passenger.getId());

        boolean created = bookingDao.createBooking(booking);
        if (created) {
            // После создания бронирования перенаправляем пользователя на страницу оплаты
            req.getSession().setAttribute("currentBooking", booking);
            resp.sendRedirect("processPayment");
        } else {
            req.setAttribute("error", "Ошибка бронирования");
            req.getRequestDispatcher("pages/booking.jsp").forward(req, resp);
        }
    }
}
