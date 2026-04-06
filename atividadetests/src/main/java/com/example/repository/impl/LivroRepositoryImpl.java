package com.example.repository.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.config.ConnectionFactory;
import com.example.model.Livro;
import com.example.repository.LivroRepository;

public class LivroRepositoryImpl implements LivroRepository {

    public Livro salvarLivro(Livro livro) throws SQLException {

        String command = """
                INSERT INTO livros (titulo, autor, ano, disponivel)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setBoolean(4, livro.isDisponivel());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                livro.setId(rs.getInt(1));
                return livro;
            }
        }
        throw new SQLException("Erro ao salvar livro no database");
    }

    @Override
    public List<Livro> buscarTodos() throws SQLException {
        List<Livro> livros = new ArrayList<>();

        String command = """
                SELECT id,
                titulo,
                autor,
                ano,
                disponivel
                FROM livros
                """;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano"),
                        rs.getBoolean("disponivel")
                );
                livros.add(livro);
            }
        }
        throw new SQLException();
    }

    @Override
    public Livro atualizarDisponibilidade(int id, boolean disponibilidade) throws SQLException {
        String command = """
                UPDATE livros
                SET disponivel = ?
                WHERE id = ?""";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setBoolean(1, disponibilidade);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        }
        throw new SQLException();
    }
}