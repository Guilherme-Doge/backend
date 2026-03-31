package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;

import com.example.model.Livro;
import com.example.model.Usuario;
import com.example.service.LivroService;

public class EmprestimoRepository {
    private Connection connection;

    public EmprestimoRepository(Connection connection) {
        this.connection = connection;
    }

    public void registerBorrow(Usuario usuario, 
                                Livro livro, 
                                LocalDate dataEmprestimo, 
                                LocalDate dataDevolucao) {
        String command = """
                INSERT INTO
                emprestimos(
                usuario_id,
                livro_id,
                data_emprestimo,
                data_devolucao)
                VALUES (?, ?, ?, ?)
                """;

    }

    public Usuario listarUsuarioPorId(int id) {
        Usuario usuario = new Usuario(0, null, null);
        String command = """
                SELECT id
                FROM usuarios
                WHERE id = ?;
                """;
        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            usuario.setId(rs.getInt("id"));
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return usuario;
    }
}
