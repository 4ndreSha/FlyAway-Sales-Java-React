package com.flyaway.servlet;

import com.flyaway.dao.PassengerDao;
import com.flyaway.entity.Passenger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private PassengerDao passengerDao = new PassengerDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Passenger passenger = new Passenger();
        passenger.setEmail(req.getParameter("email"));
        passenger.setPassword(req.getParameter("password")); // в реальном приложении пароль нужно захешировать
        passenger.setFirstName(req.getParameter("firstName"));
        passenger.setLastName(req.getParameter("lastName"));
        passenger.setPatronymic(req.getParameter("patronymic"));
        passenger.setPhone(req.getParameter("phone"));

        boolean registered = passengerDao.register(passenger);
        if (registered) {
            req.getSession().setAttribute("passenger", passenger);
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("error", "Ошибка регистрации. Попробуйте снова.");
            req.getRequestDispatcher("pages/register.jsp").forward(req, resp);
        }
    }
}
