package com.example.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import com.example.model.Emprestimo;
import com.example.model.Livro;
import com.example.model.Usuario;

public class EmprestimoRepository {

    private final Connection connection;

    public EmprestimoRepository(Connection connection) {
        this.connection = connection;
    }

    public void registerBorrow(Usuario usuario,
                               Livro livro,
                               LocalDate dataEmprestimo,
                               LocalDate dataDevolucao) {

        String command = """
                INSERT INTO emprestimos (
                    usuario_id,
                    livro_id,
                    data_emprestimo,
                    data_devolucao
                ) VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setInt(1, usuario.getId());
            stmt.setInt(2, livro.getId());
            stmt.setDate(3, Date.valueOf(dataEmprestimo));
            stmt.setDate(4, Date.valueOf(dataDevolucao));
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar empréstimo", e);
        }
    }

    public Usuario listarUsuarioPorId(int id) {

        String command = """
                SELECT id
                FROM usuarios
                WHERE id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(rs.getInt("id"), null, null);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
    }

    public Livro pegarLivroPeloEmprestimo(int idEmprestimo) {

        String command = """
                SELECT livro_id
                FROM emprestimos
                WHERE id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setInt(1, idEmprestimo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Livro livro = new Livro(null, null, 0, false);
                livro.setId(rs.getInt("livro_id"));
                return livro;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar livro pelo empréstimo", e);
        }
    }

    public void devolverLivro(int idLivro) {

        String command = """
                UPDATE livros
                SET disponivel = TRUE
                WHERE id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setInt(1, idLivro);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao devolver livro", e);
        }
    }

    public List<Emprestimo> verEmprestimos() {

        List<Emprestimo> emprestimos = new ArrayList<>();

        String command = """
                SELECT id,
                       livro_id,
                       usuario_id,
                       data_emprestimo,
                       data_devolucao
                FROM emprestimos
                """;

        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int livroId = rs.getInt("livro_id");
                Usuario usuario = new Usuario(rs.getInt("usuario_id"), null, null);
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate dataDevolucao = rs.getDate("data_devolucao").toLocalDate();

                emprestimos.add(new Emprestimo(
                        id,
                        livroId,
                        usuario,
                        dataEmprestimo,
                        dataDevolucao
                ));
            }

            return emprestimos;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar empréstimos", e);
        }
    }
}