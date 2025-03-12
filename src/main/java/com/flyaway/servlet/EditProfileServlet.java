package com.flyaway.servlet;

import com.flyaway.dao.PassengerDao;
import com.flyaway.model.Passenger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {
    private final PassengerDao passengerDao = new PassengerDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем пользователя из сессии
        Passenger passenger = (Passenger) req.getSession().getAttribute("passenger");
        if (passenger == null) {
            resp.sendRedirect("login");
            return;
        }
        req.setAttribute("passenger", passenger);
        req.getRequestDispatcher("pages/editProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Passenger passenger = (Passenger) req.getSession().getAttribute("passenger");
        if (passenger == null) {
            resp.sendRedirect("login");
            return;
        }

        // Читаем обновленные данные из формы
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String patronymic = req.getParameter("patronymic");
        String phone = req.getParameter("phone");

        // Обновляем объект пользователя
        passenger.setEmail(email);
        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        passenger.setPatronymic(patronymic);
        passenger.setPhone(phone);

        boolean updated = passengerDao.updatePassenger(passenger);
        if (updated) {
            // Обновляем данные в сессии
            req.getSession().setAttribute("passenger", passenger);
            resp.sendRedirect("account");
        } else {
            req.setAttribute("error", "Ошибка обновления профиля. Попробуйте еще раз.");
            req.setAttribute("passenger", passenger);
            req.getRequestDispatcher("pages/editProfile.jsp").forward(req, resp);
        }
    }
}
