package com.example.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private final String 
    private final String 
    private final String 

    public static Connection getConnection() {
        return DriverManager
    }
}