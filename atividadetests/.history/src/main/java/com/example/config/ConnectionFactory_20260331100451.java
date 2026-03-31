package com.example.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private final String USER
    private final String PASS
    private final String URL

    public static Connection getConnection() {
        return DriverManager
    }
}