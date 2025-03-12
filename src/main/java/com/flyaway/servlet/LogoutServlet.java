package com.flyaway.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // получаем сессию, если есть
        if (session != null) {
            session.invalidate(); // завершаем сессию
        }
        resp.sendRedirect("index.jsp"); // перенаправляем на главную страницу
    }
}
