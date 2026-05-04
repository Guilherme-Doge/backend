package com.weg.minha_primeira_api.repo;

import com.weg.minha_primeira_api.infra.Conexao;
import com.weg.minha_primeira_api.model.Contato;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContatoRepoImpl implements ContatoRepo {

    @Override
    public Contato createContato(Contato contato) throws SQLException {
        String sql = """
                INSERT INTO Contatos(
                    nome,
                    numero)
                VALUES (?,?);""";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getNumero());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    contato.setId(rs.getLong(1));
                } else {
                    throw new SQLDataException("Contato não inserido");
                }
            }
        }
        return contato;
    }

    @Override
    public List<Contato> readAllContato() throws SQLException {
        List<Contato> contatos = new ArrayList<>();

        String sql = """
                SELECT id,
                        nome,
                        numero
                FROM Contatos""";

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contatos.add(new Contato(rs.getLong("id"),
                                            rs.getString("nome"),
                                            rs.getString("numero")));
                }
            }
        }
        return contatos;
    }

    @Override
    public void updateContatoById(Contato contato) throws SQLException {
        String sql = """
                UPDATE Contatos
                SET nome = ?, numero = ?
                WHERE id = ?;""";

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getNumero());
            stmt.setLong(3, contato.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteContatoById(long id) throws SQLException {
        String sql = """
                DELETE FROM Contatos
                WHERE id = ?;""";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
}
