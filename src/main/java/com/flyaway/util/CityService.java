package com.flyaway.util;

import java.util.ArrayList;
import java.util.List;

import com.flyaway.model.City;

public class CityService {
    public List<City> getCities() {
        List<City> cities = new ArrayList<>();

        // Добавляем города с названиями аэропортов
        cities.add(new City("Якутск", "Якутск", 62.0933, 129.7705));
        cities.add(new City("Мирный", "Мирный", 62.5349, 114.0389));
        cities.add(new City("Хабаровск", "Хабаровск-Новый", 48.5280, 135.1885));
        cities.add(new City("Петропавловск-Камчатский", "Елизово", 53.1678, 158.4535));
        cities.add(new City("Иркутск", "Иркутск", 52.2680, 104.3889));
        cities.add(new City("Владивосток", "Владивосток", 43.3989, 132.1480));
        cities.add(new City("Санкт-Петербург", "Пулково", 59.8003, 30.2625));
        cities.add(new City("Калининград", "Храброво", 54.8900, 20.5922));
        cities.add(new City("Кемерово", "Кемерово", 55.2706, 86.1072));
        cities.add(new City("Челябинск", "Челябинск", 55.3058, 61.5033));
        cities.add(new City("Магнитогорск", "Магнитогорск", 53.3931, 58.7557));
        cities.add(new City("Пермь", "Пермь", 57.9147, 56.0219));
        cities.add(new City("Сургут", "Сургут", 61.3430, 73.4018));
        cities.add(new City("Брянск", "Брянск", 53.2140, 34.1764));
        cities.add(new City("Минеральные Воды", "Минеральные Воды", 44.2257, 43.0819));
        cities.add(new City("Ставрополь", "Ставрополь", 45.1092, 42.1128));
        cities.add(new City("Астрахань", "Астрахань", 46.2833, 48.0033));
        cities.add(new City("Нижневартовск", "Нижневартовск", 60.9496, 76.4842));
        cities.add(new City("Екатеринбург", "Кольцово", 56.8011, 60.6396));
        cities.add(new City("Москва", "Шереметьево", 55.9726, 37.4146));
        cities.add(new City("Москва", "Домодедово", 55.4143, 37.9005));
        cities.add(new City("Москва", "Внуково", 55.6049, 37.2823));
        cities.add(new City("Воронеж", "Воронеж", 51.8142, 39.2296));
        cities.add(new City("Сыктывкар", "Сыктывкар", 61.6477, 50.8451));
        cities.add(new City("Самара", "Курумоч", 53.5049, 50.1644));
        cities.add(new City("Тюмень", "Рощино", 57.1896, 65.3243));
        cities.add(new City("Нижний Новгород", "Стригино", 56.2301, 43.7840));
        cities.add(new City("Томск", "Богашёво", 56.3831, 85.2083));
        cities.add(new City("Усть-Илимск", "Усть-Илимск", 58.1367, 102.5650));
        cities.add(new City("Норильск", "Норильск", 69.3111, 87.3325));

        return cities;
    }
}