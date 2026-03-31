package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                SELECT id
                FROM usuarios
                WHERE id = ?;
                """;
        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
