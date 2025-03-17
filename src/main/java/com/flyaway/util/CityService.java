package com.flyaway.util;

import java.util.ArrayList;
import java.util.List;

import com.flyaway.dao.AirportDao;
import com.flyaway.entity.Airport;
import com.flyaway.entity.City;

public class CityService {
    private AirportDao airportDao = new AirportDao();

    public List<City> getCities() {
        List<Airport> airports = airportDao.getAllAirports();
        List<City> cities = new ArrayList<>();

        for (Airport airport : airports) {
            String cityName = airport.getCity();
            String airportName = airport.getAirportName();
            String coordinates = airport.getCoordinates();

            if (coordinates == null || coordinates.trim().isEmpty()) {
                continue;
            }
            coordinates = coordinates.replaceAll("[()]", "");
            String[] parts = coordinates.split(",");
            if (parts.length != 2) {
                continue;
            }

            try {
                double latitude = Double.parseDouble(parts[1].trim());
                double longitude = Double.parseDouble(parts[0].trim());

                City city = new City(cityName, airportName, latitude, longitude);
                cities.add(city);
            } catch (NumberFormatException e) {
                System.err.println("Ошибка в координатах аэропорта " + airport.getAirportCode() + ": " + coordinates);
            }
        }

        return cities;
    }
}