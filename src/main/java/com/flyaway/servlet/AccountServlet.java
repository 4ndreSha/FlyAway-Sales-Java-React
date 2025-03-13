package com.flyaway.servlet;

import com.flyaway.dao.TicketDao;
import com.flyaway.entity.Passenger;
import com.flyaway.entity.Ticket;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    private final TicketDao ticketDao = new TicketDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Passenger passenger = (Passenger) req.getSession().getAttribute("passenger");
        if (passenger == null) {
            resp.sendRedirect("login");
            return;
        }
        List<Ticket> tickets = ticketDao.getTicketsByPassenger(passenger.getId());
        req.setAttribute("tickets", tickets);
        req.getRequestDispatcher("pages/account.jsp").forward(req, resp);
    }
}
