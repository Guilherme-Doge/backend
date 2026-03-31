package com.example.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private final String USER = "";
    private final String PASS = "postgres.gbtgadyebrqyvkmegtrs";
    private final String URL = "Dgui@132547698";

    public static Connection getConnection() {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}