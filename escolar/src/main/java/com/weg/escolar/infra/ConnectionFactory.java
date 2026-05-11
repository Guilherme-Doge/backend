package com.weg.escolar.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private final static String URL = "jdbc:mysql://localhost:3356/escolar?useSSL=false&serverTimezone=UTC";
    private final static String USER = "root";
    private final static String PW = "mysqlPW";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PW);
    }
}
