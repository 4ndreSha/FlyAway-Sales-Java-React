package com.flyaway.servlet;

import com.flyaway.dao.AircraftDao;
import com.flyaway.model.Aircraft;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/aircrafts")
public class AircraftsServlet extends HttpServlet {
    private AircraftDao aircraftDao = new AircraftDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Aircraft> aircrafts = aircraftDao.getAllAircrafts();
        req.setAttribute("aircrafts", aircrafts);
        req.getRequestDispatcher("pages/aircrafts.jsp").forward(req, resp);
    }
}
