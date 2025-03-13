package com.flyaway.servlet;

import com.flyaway.dao.PassengerDao;
import com.flyaway.entity.Passenger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private PassengerDao passengerDao = new PassengerDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Passenger passenger = passengerDao.authenticate(email, password);
        if (passenger != null) {
            req.getSession().setAttribute("passenger", passenger);
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("error", "Неверные учетные данные");
            req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
        }
    }
}
