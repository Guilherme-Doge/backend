package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Livro;

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
        Livro livro = new Livro(0, null, null, 0, true);
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

    public void marcarLivroComoNaoDisponivel(int id) {
        String command = """
                UPDATE livros
                SET disponivel = FALSE
                WHERE id = ?;
                """;   
        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        String command = """
                SELECT id, 
                """;

        try (prepareStatement stmt = connection..prepareStatement(command)) {

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return livros;
    }
}