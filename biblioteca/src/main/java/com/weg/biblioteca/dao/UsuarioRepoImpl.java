package com.weg.biblioteca.dao;

import com.weg.biblioteca.infra.ConexaoFactory;
import com.weg.biblioteca.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepoImpl implements UsuarioRepo {

    @Override
    public Usuario salvar(Usuario usuario) throws SQLException {
        String sql = """
                INSERT INTO usuario(
                    nome,
                    email)
                VALUES (?,?);""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                usuario.setId(rs.getLong(1));

                return usuario;
            } else {
                throw new SQLDataException("Usuário não cadastrado");
            }
        }
    }

    @Override
    public List<Usuario> buscarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = """
                SELECT id,
                        nome,
                        email
                FROM usuario;""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email")));
            }
        }
        return usuarios;
    }

    @Override
    public Optional<Usuario> buscarPorId(long id) throws SQLException {
        Usuario usuario = new Usuario();
        String sql = """
                SELECT nome,
                        email
                FROM usuario
                WHERE id = ?;""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario.setId(id);
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
            }
        }

        return Optional.of(usuario);
    }

    @Override
    public Usuario atualizar(Usuario usuario) throws SQLException {
        String sql = """
                UPDATE usuario
                SET nome = ?,
                    email = ?
                WHERE id = ?;""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setLong(3, usuario.getId());

            stmt.executeUpdate();

            return usuario;
        }
    }

    public boolean existePorId(long id) throws SQLException {
        Usuario usuario = new Usuario();
        String sql = """
                SELECT id
                FROM usuario
                WHERE id = ?;""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario.setId(id);
            } else {
                usuario.setId(0);
            }
        }

        if (usuario.getId() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public void deletar(long id) throws SQLException {
        String sql = """
                DELETE FROM usuario
                WHERE id = ?;""";

        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
}