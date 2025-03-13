package com.flyaway.servlet;

import com.flyaway.dao.AirportDao;
import com.flyaway.entity.Airport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/airports")
public class AirportsServlet extends HttpServlet {
    private AirportDao airportDao = new AirportDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Airport> airports = airportDao.getAllAirports();
        req.setAttribute("airports", airports);
        req.getRequestDispatcher("pages/airports.jsp").forward(req, resp);
    }
}
