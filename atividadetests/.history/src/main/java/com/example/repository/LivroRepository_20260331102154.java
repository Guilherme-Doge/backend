package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            stmt.setString(publicationYear, command);
            ResultSet rs 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}