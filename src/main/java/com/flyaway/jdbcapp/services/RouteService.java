package com.flyaway.jdbcapp.services;

import com.flyaway.jdbcapp.dao.RouteDao;
import com.flyaway.jdbcapp.entity.Route;
import java.util.List;
import java.util.Scanner;

public class RouteService {
    private final RouteDao routeDao;
    private final Scanner scanner;

    public RouteService(RouteDao routeDao, Scanner scanner) {
        this.routeDao = routeDao;
        this.scanner = scanner;
    }

    public void listAllRoutes() {
        List<Route> routes = routeDao.getAllRoutes();
        if (routes.isEmpty()) {
            System.out.println("Нет данных о маршрутах.");
        } else {
            System.out.println("\nСписок маршрутов:");
            routes.forEach(System.out::println);
        }
    }
}