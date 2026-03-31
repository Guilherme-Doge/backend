package com.example.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private final String USER = "";
    private final String PASS = "Dgui@132547698";
    private final String URL = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:5432/postgres";

    public static Connection getConnection() {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}