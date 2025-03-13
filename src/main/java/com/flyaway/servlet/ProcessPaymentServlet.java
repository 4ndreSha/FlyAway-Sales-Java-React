package com.flyaway.servlet;

import com.flyaway.dao.BookingDao;
import com.flyaway.dao.PaymentDao;
import com.flyaway.entity.Booking;
import com.flyaway.entity.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/processPayment")
public class ProcessPaymentServlet extends HttpServlet {
    private PaymentDao paymentDao = new PaymentDao();
    private BookingDao bookingDao = new BookingDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Отобразить страницу оплаты (например, payment.jsp)
        req.getRequestDispatcher("pages/payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Booking booking = (Booking) req.getSession().getAttribute("currentBooking");
        if (booking == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        String paymentMethod = req.getParameter("paymentMethod");
        // Дополнительные данные по оплате можно получить из формы
        Payment payment = new Payment();
        payment.setBookingRef(booking.getBookRef());
        payment.setAmount(booking.getTotalAmount());
        payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));
        payment.setPaymentMethod(paymentMethod);
        // Симуляция успешного платежа:
        payment.setStatus("PAID");

        boolean paymentSuccess = paymentDao.processPayment(payment);
        if (paymentSuccess) {
            // Обновляем статус бронирования
            booking.setStatus("CONFIRMED");
            bookingDao.createBooking(booking); // или обновляем запись через updateBooking(...)
            req.getSession().removeAttribute("currentBooking");
            req.setAttribute("booking", booking);
            req.getRequestDispatcher("pages/bookingConfirmation.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Ошибка оплаты");
            req.getRequestDispatcher("pages/payment.jsp").forward(req, resp);
        }
    }
}
