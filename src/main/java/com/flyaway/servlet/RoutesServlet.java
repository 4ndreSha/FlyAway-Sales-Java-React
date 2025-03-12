package com.flyaway.servlet;

import com.flyaway.dao.RouteDao;
import com.flyaway.model.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/routes")
public class RoutesServlet extends HttpServlet {
    private RouteDao routeDao = new RouteDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Route> routes = routeDao.getAllRoutes();
        req.setAttribute("routes", routes);
        req.getRequestDispatcher("pages/routes.jsp").forward(req, resp);
    }
}
