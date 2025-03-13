package com.flyaway.servlet;

import com.google.gson.Gson;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import com.flyaway.entity.City;
import com.flyaway.util.CityService;

@WebServlet("/cities")
public class CityServlet extends HttpServlet {
    private final CityService cityService = new CityService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        List<City> cities = cityService.getCities();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(gson.toJson(cities));
    }
}