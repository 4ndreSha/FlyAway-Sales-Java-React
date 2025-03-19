package com.flyaway.jdbcapp.entity;

public class Airport {
    private int airportCode;
    private String airportName;
    private String city;
    private double latitude;
    private double longitude;
    private String timezone;

    // Геттеры и сеттеры
    public int getAirportCode() {
        return airportCode;
    }
    public void setAirportCode(int airportCode) {
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
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
