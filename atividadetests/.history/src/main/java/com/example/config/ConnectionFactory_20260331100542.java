package com.example.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private final String USER = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:5432/postgres";
    private final String PASS = "postgres.gbtgadyebrqyvkmegtrs";
    private final String URL = "Dgui@132547698";

    public static Connection getConnection() {
        return DriverManager
    }
}