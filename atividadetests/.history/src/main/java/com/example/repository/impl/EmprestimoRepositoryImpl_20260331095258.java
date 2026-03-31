package com.example.repository.impl;

import java.sql.Connection;

import com.example.repository.EmprestimoRepository;

public class EmprestimoRepositoryImpl implements EmprestimoRepository {

    private Connection connection;

    public RepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    
}
