package com.flyaway.servlet;

import com.flyaway.dao.TicketDao;
import com.flyaway.model.TicketDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ticketDetails")
public class TicketDetailsServlet extends HttpServlet {
    private final TicketDao ticketDao = new TicketDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ticketNo = req.getParameter("ticketNo");
        if (ticketNo == null || ticketNo.trim().isEmpty()) {
            resp.sendRedirect("account");
            return;
        }
        TicketDetails details = ticketDao.getTicketDetailsByTicketNo(ticketNo);
        if (details == null) {
            req.setAttribute("error", "Билет не найден");
            req.getRequestDispatcher("pages/account.jsp").forward(req, resp);
        } else {
            req.setAttribute("ticketDetails", details);
            req.getRequestDispatcher("pages/ticketDetails.jsp").forward(req, resp);
        }
    }
}
