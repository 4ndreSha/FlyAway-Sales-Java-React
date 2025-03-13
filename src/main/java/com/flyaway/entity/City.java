package com.flyaway.entity;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("cityName") // Явное указание имени для JSON
    private String cityName;

    @SerializedName("airportName") // Явное указание имени для JSON
    private String airportName;
    private double latitude;
    private double longitude;

    // Конструктор
    public City(String cityName, String airportName, double latitude, double longitude) {
        this.cityName = cityName;
        this.airportName = airportName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Геттеры (должны соответствовать именам или иметь аннотации)
    public String getCityName() {
        return cityName;
    }

    public String getAirportName() {
        return airportName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}