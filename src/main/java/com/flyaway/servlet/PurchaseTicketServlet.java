package com.flyaway.servlet;

import com.flyaway.dao.PurchaseTicketDao;
import com.flyaway.entity.Passenger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/purchaseTicket")
public class PurchaseTicketServlet extends HttpServlet {
    private PurchaseTicketDao purchaseTicketDao = new PurchaseTicketDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Passenger passenger = (Passenger) session.getAttribute("passenger");

        if (passenger == null) {
            resp.sendRedirect("login");
            return;
        }

        // Проверка данных пассажира
        if (passenger.getEmail() == null || passenger.getPhone() == null) {
            req.setAttribute("error", "Необходимо обновить контактные данные в профиле");
            req.getRequestDispatcher("account").forward(req, resp);
            return;
        }

        // Формирование contact_data
        String contactData = String.format(
                "{\"email\": \"%s\", \"phone\": \"%s\"}",
                passenger.getEmail(),
                passenger.getPhone()
        );

        String flightId = req.getParameter("flightId");
        String amountStr = req.getParameter("amount");
        String fareConditions = req.getParameter("fareConditions");

        BigDecimal amount = new BigDecimal(amountStr);
        boolean success = purchaseTicketDao.purchaseTicket(
                flightId,
                passenger.getId(),
                new BigDecimal(amountStr),
                fareConditions,
                contactData // Передаем сформированные данные
        );
        if (success) {
            req.getRequestDispatcher("pages/bookingConfirmation.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Ошибка при покупке билета. Попробуйте снова.");
            req.getRequestDispatcher("pages/flightDetails.jsp").forward(req, resp);
        }
    }
}
