package com.flyaway.jdbcapp.entity;

public class Route {
    private String flightNo;
    private int departureAirport;
    private String departureAirportName;
    private String departureCity;
    private int arrivalAirport;
    private String arrivalAirportName;
    private String arrivalCity;
    private int aircraftCode;
    private String duration;       // Интервал в виде строки
    private String daysOfWeek;     // Можно сохранить как строку (например, "[1,2,3]")

    // Геттеры и сеттеры
    public String getFlightNo() {
        return flightNo;
    }
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }
    public int getDepartureAirport() {
        return departureAirport;
    }
    public void setDepartureAirport(int departureAirport) {
        this.departureAirport = departureAirport;
    }
    public String getDepartureAirportName() {
        return departureAirportName;
    }
    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }
    public String getDepartureCity() {
        return departureCity;
    }
    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }
    public int getArrivalAirport() {
        return arrivalAirport;
    }
    public void setArrivalAirport(int arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
    public String getArrivalAirportName() {
        return arrivalAirportName;
    }
    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }
    public String getArrivalCity() {
        return arrivalCity;
    }
    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
    public int getAircraftCode() {
        return aircraftCode;
    }
    public void setAircraftCode(int aircraftCode) {
        this.aircraftCode = aircraftCode;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getDaysOfWeek() {
        return daysOfWeek;
    }
    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    @Override
    public String toString() {
        return "Route{" +
                "flightNo='" + flightNo + '\'' +
                ", departureAirport='" + departureAirport + '\'' +
                ", departureAirportName='" + departureAirportName + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", arrivalAirportName='" + arrivalAirportName + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", aircraftCode='" + aircraftCode + '\'' +
                ", duration='" + duration + '\'' +
                ", daysOfWeek=" + daysOfWeek +
                '}';
    }
}
