package com.flyaway.servlet;

import com.flyaway.dao.FlightDao;
import com.flyaway.model.Flight;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/flightDetails")
public class FlightDetailsServlet extends HttpServlet {
    private FlightDao flightDao = new FlightDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flightIdStr = req.getParameter("flightId");
        if (flightIdStr == null) {
            resp.sendRedirect("searchFlights");
            return;
        }
        int flightId = Integer.parseInt(flightIdStr);
        Flight flight = flightDao.getFlightById(flightId);

        if (flight == null) {
            req.setAttribute("error", "Рейс не найден");
            req.getRequestDispatcher("pages/searchResults.jsp").forward(req, resp);
        } else {
            // Устанавливаем базовую цену
            flight.setPrice(new BigDecimal("7000"));
            req.setAttribute("flight", flight);
            req.getRequestDispatcher("pages/flightDetails.jsp").forward(req, resp);
        }
    }
}
