package com.weg.minha_primeira_api.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private final static String URL = "jdbc:mysql://localhost:3356/MYSQLPRIMEIRAAPI?useSSL=false&serverTimezone=UTC";

    private final static String USER = "root";

    private final static String PW = "mysqlPW";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PW);
    }
}