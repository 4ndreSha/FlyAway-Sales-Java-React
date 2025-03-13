package com.flyaway.servlet;

import com.flyaway.dao.FlightDao;
import com.flyaway.entity.Flight;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/flights")
public class FlightsServlet extends HttpServlet {
    private FlightDao flightDao = new FlightDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Flight> flights = flightDao.getAllFlights();
        req.setAttribute("flights", flights);
        req.getRequestDispatcher("pages/flights.jsp").forward(req, resp);
    }
}
