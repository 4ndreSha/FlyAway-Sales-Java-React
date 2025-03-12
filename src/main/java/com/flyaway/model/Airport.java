package com.flyaway.model;

public class Airport {
    private String airportCode;
    private String airportName;
    private String city;
    private String coordinates; // Можно сохранить как строку
    private String timezone;

    // Геттеры и сеттеры
    public String getAirportCode() {
        return airportCode;
    }
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }
    public String getAirportName() {
        return airportName;
    }
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
    public String getTimezone() {
        return timezone;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportCode='" + airportCode + '\'' +
                ", airportName='" + airportName + '\'' +
                ", city='" + city + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
