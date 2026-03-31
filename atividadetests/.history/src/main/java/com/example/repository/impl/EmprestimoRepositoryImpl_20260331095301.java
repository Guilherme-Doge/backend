package com.example.repository.impl;

import java.sql.Connection;

import com.example.repository.EmprestimoRepository;

public class EmprestimoRepositoryImpl implements EmprestimoRepository {

    private Connection connection;

    public EmprestimoRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    
}
