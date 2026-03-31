package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.example.model.Usuario;

public class EmprestimoRepository {
    private Connection connection;

    public EmprestimoRepository(Connection connection) {
        this.connection = connection;
    }

    public void registerBorrow() {

    }

    public Usuario listarUsuarioPorId(int id) {
        Usuario usuario = new Usuario(0, null, null)
        String command = """
                SELECT id
                FROM usuarios
                WHERE id = ?;
                """;
        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            usuario.setId();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return usuario;
    }
}
