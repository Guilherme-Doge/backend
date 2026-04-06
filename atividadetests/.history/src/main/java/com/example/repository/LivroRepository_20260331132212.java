package com.example.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Livro;

public class LivroRepository {

    private final Connection connection;

    public LivroRepository(Connection connection) {
        this.connection = connection;
    }

    public void registerBook(String title, String author, int publicationYear) {

        String command = """
                INSERT INTO livros (titulo, autor, ano)
                VALUES (?, ?, ?)
                """;

        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, publicationYear);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar livro", e);
        }
    }

    public Livro listarLivroPorId(int id) {

        String command = """
                SELECT id, titulo, autor, ano, disponivel
                FROM livros
                WHERE id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano"),
                        rs.getBoolean("disponivel")
                );
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar livro", e);
        }
    }

    public void marcarLivroComoNaoDisponivel(int id) {

        String command = """
                UPDATE livros
                SET disponivel = FALSE
                WHERE id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar livro", e);
        }
    }

    public void marcarLivroComoDisponivel(int id) {

        String command = """
                UPDATE livros
                SET disponivel = TRUE
                WHERE id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar livro", e);
        }
    }

    public List<Livro> listarLivros() {

        List<Livro> livros = new ArrayList<>();

        String command = """
                SELECT id, titulo, autor, ano, disponivel
                FROM livros
                """;

        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                livros.add(new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano"),
                        rs.getBoolean("disponivel")
                ));
            }

            return livros;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar livros", e);
        }
    }
}