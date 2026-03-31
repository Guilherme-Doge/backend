package com.example.repository;

import java.sql.Connection;

import com.example.model.Usuario;

public class EmprestimoRepository {
    private Connection connection;

    public EmprestimoRepository(Connection connection) {
        this.connection = connection;
    }

    public void registerBorrow() {

    }

    public Usuario listarUsuarioPorId(int id) {
        String command = """
                SELECT id, nome
                """;
    }
}
