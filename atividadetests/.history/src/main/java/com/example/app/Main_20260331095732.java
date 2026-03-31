package com.example.app;

import java.sql.Connection;

import com.example.config.ConnectionFactory;

public class Main {
    public static void main(String[] args) {
        Connection conn = ConnectionFactory.getConnection();

        LivroRepository repo = new LivroRepositoryImpl(conn);
        LivroService service = new LivroService(repo);
    }
}