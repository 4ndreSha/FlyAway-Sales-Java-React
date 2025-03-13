package com.flyaway.servlet;

import com.flyaway.dao.FlightDao;
import com.flyaway.entity.Flight;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchFlights")
public class SearchFlightsServlet extends HttpServlet {
    private final FlightDao flightDao = new FlightDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String airport = req.getParameter("airport");
        if (airport == null || airport.trim().isEmpty()) {
            req.setAttribute("error", "Пожалуйста, выберите аэропорт.");
            req.getRequestDispatcher("pages/searchFlights.jsp").forward(req, resp);
            return;
        }

        // Получаем параметры сортировки и фильтрации
        String depTimeSort = req.getParameter("depTimeSort"); // ожидается "time_asc" или "time_desc"
        String depStatus = req.getParameter("depStatus"); // например: All, Scheduled, On Time, ...
        String arrTimeSort = req.getParameter("arrTimeSort");
        String arrStatus = req.getParameter("arrStatus");
        if (depTimeSort == null || depTimeSort.trim().isEmpty()) {
            depTimeSort = "time_asc";
        }
        if (arrTimeSort == null || arrTimeSort.trim().isEmpty()) {
            arrTimeSort = "time_asc";
        }
        if (depStatus == null || depStatus.trim().isEmpty()) {
            depStatus = "All";
        }
        if (arrStatus == null || arrStatus.trim().isEmpty()) {
            arrStatus = "All";
        }

        List<Flight> depFlights = flightDao.getFlightsByDeparture(airport.trim(), depTimeSort, depStatus);
        List<Flight> arrFlights = flightDao.getFlightsByArrival(airport.trim(), arrTimeSort, arrStatus);

        req.setAttribute("depFlights", depFlights);
        req.setAttribute("arrFlights", arrFlights);
        req.setAttribute("airport", airport);
        // Передаем текущие параметры сортировки и фильтрации для формирования ссылок
        req.setAttribute("depTimeSort", depTimeSort);
        req.setAttribute("depStatus", depStatus);
        req.setAttribute("arrTimeSort", arrTimeSort);
        req.setAttribute("arrStatus", arrStatus);

        req.getRequestDispatcher("pages/searchResults.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String airport = req.getParameter("airport");
        if (airport == null || airport.trim().isEmpty()) {
            req.getRequestDispatcher("pages/searchFlights.jsp").forward(req, resp);
            return;
        }

        String depTimeSort = req.getParameter("depTimeSort") != null ? req.getParameter("depTimeSort") : "time_asc";
        String depStatus = req.getParameter("depStatus") != null ? req.getParameter("depStatus") : "All";
        String arrTimeSort = req.getParameter("arrTimeSort") != null ? req.getParameter("arrTimeSort") : "time_asc";
        String arrStatus = req.getParameter("arrStatus") != null ? req.getParameter("arrStatus") : "All";

        List<Flight> depFlights = flightDao.getFlightsByDeparture(airport.trim(), depTimeSort, depStatus);
        List<Flight> arrFlights = flightDao.getFlightsByArrival(airport.trim(), arrTimeSort, arrStatus);

        req.setAttribute("depFlights", depFlights);
        req.setAttribute("arrFlights", arrFlights);
        req.setAttribute("airport", airport);
        req.setAttribute("depTimeSort", depTimeSort);
        req.setAttribute("depStatus", depStatus);
        req.setAttribute("arrTimeSort", arrTimeSort);
        req.setAttribute("arrStatus", arrStatus);

        req.getRequestDispatcher("pages/searchResults.jsp").forward(req, resp);
    }
}
