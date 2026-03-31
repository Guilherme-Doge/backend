package com.example.repository;

import java.sql.Connection;

public class LivroRepository {
    private Connection connection;

    public LivroRepository(Connection connection) {
        this.connection = connection;
    }

    
}
