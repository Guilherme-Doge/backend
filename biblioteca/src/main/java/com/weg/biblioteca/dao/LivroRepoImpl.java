package com.weg.biblioteca.dao;

import com.weg.biblioteca.infra.ConexaoFactory;
import com.weg.biblioteca.model.Livro;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LivroRepoImpl implements LivroRepo {

    @Override
    public Livro salvar(Livro livro) throws SQLException {
        String sql = """
                INSERT INTO livro(
                    titulo,
                    autor,
                    ano_publicacao)
                VALUES (?,?,?);""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                livro.setId(rs.getLong(1));

                return livro;
            } else {
                throw new SQLDataException("Livro não cadastrado");
            }
        }
    }

    @Override
    public List<Livro> buscarTodos() throws SQLException {
        List<Livro> livros = new ArrayList<>();

        String sql = """
                SELECT id,
                        autor,
                        titulo,
                        ano_publicacao
                FROM livro;""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                livros.add(new Livro(rs.getLong("id"),
                                    rs.getString("autor"),
                                    rs.getString("titulo"),
                                    rs.getInt("ano_publicacao")));
            }
        }
        return livros;
    }

    @Override
    public Optional<Livro> buscarPorId(long id) throws SQLException {
        Livro livro = new Livro();
        String sql = """
                SELECT autor,
                        titulo,
                        ano_publicacao
                FROM livro
                WHERE id = ?;""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                livro.setId(id);
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
            }
        }

        return Optional.of(livro);
    }

    @Override
    public Livro atualizar(long id) throws SQLException {
        return null;
    }

    @Override
    public void deletar(long id) throws SQLException {

    }
}