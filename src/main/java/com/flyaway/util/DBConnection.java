package com.flyaway.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // URL подключения. Убедись, что порт и название БД соответствуют твоей конфигурации.
    private static final String URL = "jdbc:postgresql://localhost:5432/flyawaybase";
    private static final String USER = "postgres";       // замени на свой логин
    private static final String PASSWORD = "6474";   // замени на свой пароль

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}