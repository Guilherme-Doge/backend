package com.weg.biblioteca.dao;

import com.weg.biblioteca.infra.ConexaoFactory;
import com.weg.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmprestimoRepoImpl implements EmprestimoRepo{

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException {
        String sql = """
                INSERT INTO emprestimo(
                    livro_id,
                    usuario_id,
                    data_emprestimo,
                    data_devolucao)
                VALUES (?,?,?,?);""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, emprestimo.getLivroId());
            stmt.setLong(2, emprestimo.getUsuarioId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                emprestimo.setId(rs.getLong(1));

                return emprestimo;
            } else {
                throw new SQLDataException("Emprestimo não cadastrado");
            }
        }
    }

    @Override
    public List<Emprestimo> buscarTodos() throws SQLException {
        List<Emprestimo> emprestimos = new ArrayList<>();

        String sql = """
                SELECT id,
                        livro_id,
                        usuario_id,
                        data_emprestimo,
                        data_devolucao
                FROM emprestimo;""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                emprestimos.add(new Emprestimo(rs.getLong("id"),
                        rs.getLong("livro_id"),
                        rs.getLong("usuario_id"),
                        rs.getDate("data_emprestimo").toLocalDate(),
                        rs.getDate("data_devolucao").toLocalDate()));
            }
        }
        return emprestimos;
    }

    @Override
    public Optional<Emprestimo> buscarPorId(long id) throws SQLException {
        Emprestimo emprestimo = new Emprestimo();

                String sql = """
                SELECT livro_id,
                        usuario_id,
                        data_emprestimo,
                        data_devolucao
                FROM emprestimo
                WHERE id = ?
                """;

        try (Connection conn = ConexaoFactory.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                emprestimo = new Emprestimo(id,
                                        rs.getLong("livro_id"),
                                        rs.getLong("usuario_id"),
                                        rs.getDate("data_emprestimo").toLocalDate(),
                                        rs.getDate("data_devolucao").toLocalDate());
            }
        }
        return Optional.of(emprestimo);
    }

    @Override
    public Emprestimo atualizar(Emprestimo emprestimo) throws SQLException {
        String sql = """
                UPDATE emprestimo
                SET livro_id = ?,
                    usuario_id = ?,
                    data_emprestimo = ?,
                    data_devolucao = ?
                WHERE id = ?""";

        try (Connection conn = ConexaoFactory.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, emprestimo.getLivroId());
            stmt.setLong(2, emprestimo.getUsuarioId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setLong(5, emprestimo.getId());

            stmt.executeUpdate();

            return emprestimo;
        }
    }

    @Override
    public boolean existePorId(long id) throws SQLException {
        Emprestimo emprestimo = new Emprestimo();
        String sql = """
                SELECT id
                FROM emprestimo
                WHERE id = ?;""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                emprestimo.setId(id);
            } else {
                emprestimo.setId(0);
            }
        }

        if (emprestimo.getId() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public void deletar(long id) throws SQLException {
        String sql = """
                DELETE FROM emprestimo
                WHERE id = ?;""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
}
