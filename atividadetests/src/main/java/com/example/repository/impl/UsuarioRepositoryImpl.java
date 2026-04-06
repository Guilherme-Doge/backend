package com.example.repository.impl;

import java.sql.Connection;

public class UsuarioRepositoryImpl {
    private Connection connection;

    public UsuarioRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
}