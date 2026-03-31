package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.Livro;
import com.example.model.Usuario;

public class LivroRepository {
    private Connection connection;

    public LivroRepository(Connection connection) {
        this.connection = connection;
    }

    public void registerBook(String title, String author, int publicationYear) {
        String command = """
                INSER INTO
                livros(titulo, autor, ano)
                VALUES (?, ?, ?);
                """;
        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, publicationYear);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Livro listarLivroPorId(int id) {
        Livro livro = new Livro(0, null, null, null, null);
        String command = """
                SELECT id
                FROM livros
                WHERE id = ?;
                """;
        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            livro.setId(rs.getInt("id"));
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return livro;
    }
}