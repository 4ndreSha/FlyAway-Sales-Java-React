package com.flyaway.jdbcapp.entity;

public class Aircraft {
    private int aircraftCode;
    private String model;
    private int range;

    // Геттеры и сеттеры
    public int getAircraftCode() {
        return aircraftCode;
    }
    public void setAircraftCode(int aircraftCode) {
        this.aircraftCode = aircraftCode;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "aircraftCode='" + aircraftCode + '\'' +
                ", model='" + model + '\'' +
                ", range=" + range +
                '}';
    }
}
